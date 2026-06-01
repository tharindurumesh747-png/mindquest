package com.example.data

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

object QuestionGenerator {
    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun generateQuestions(grade: Int, subject: String, language: String): List<Question> = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        if (apiKey.isEmpty() || apiKey == "GEMINI_API_KEY_DEFAULT_VALUE") {
            throw Exception("Gemini API Key is not set or empty in system secrets!")
        }

        val prompt = if (language == "si") {
            """
Generate exactly 20 multiple choice questions for a Grade $grade Sri Lankan student studying $subject.
IMPORTANT: Write ALL questions and ALL answer options in Sinhala language (සිංහල).
Use proper Sinhala script.
Follow Sri Lankan school curriculum exactly.
Grade 1-3: very simple basic questions
Grade 4-6: intermediate level questions
Grade 7-9: advanced level questions
Grade 10: difficult exam level questions
All 20 questions must be different from each other.
Cover different topics within the subject.
Mix easy, medium and hard difficulty levels.
Return ONLY a JSON array, no other text:
[
  {
    "question": "string in Sinhala",
    "options": ["string", "string", "string", "string"] in Sinhala,
    "correctAnswer": 0,
    "difficulty": "easy/medium/hard"
  }
]
            """.trimIndent()
        } else {
            """
Generate exactly 20 multiple choice questions for a Grade $grade Sri Lankan student studying $subject.
Follow Sri Lankan school curriculum exactly.
Grade 1-3: very simple basic questions
Grade 4-6: intermediate level questions
Grade 7-9: advanced level questions
Grade 10: difficult exam level questions
All 20 questions must be different from each other.
Cover different topics within the subject.
Mix easy, medium and hard difficulty levels.
Return ONLY a JSON array, no other text:
[
  {
    "question": "string",
    "options": ["string", "string", "string", "string"],
    "correctAnswer": 0,
    "difficulty": "easy/medium/hard"
  }
]
            """.trimIndent()
        }

        val escapedPrompt = escapeJson(prompt)
        val requestJson = """
        {
          "contents": [
            {
              "parts": [
                {
                  "text": "$escapedPrompt"
                }
              ]
            }
          ],
          "generationConfig": {
            "responseMimeType": "application/json",
            "maxOutputTokens": 4000
          }
        }
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = requestJson.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=$apiKey")
            .post(requestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    val errorPayload = response.body?.string() ?: ""
                    val errorMsg = "Gemini API HTTP Error Code: ${response.code}. Payload: $errorPayload"
                    android.util.Log.e("QuestionGenerator", errorMsg)
                    throw Exception(errorMsg)
                }
                val body = response.body?.string() ?: throw Exception("Empty response body from Gemini API")
                try {
                    parseQuestionsJson(body, grade, subject, language)
                } catch (pe: Exception) {
                    android.util.Log.e("QuestionGenerator", "JSON parsing of Gemini response failed! Raw response payload: $body", pe)
                    throw Exception("Failed to parse Gemini generated questions list: ${pe.message}")
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("QuestionGenerator", "Critical failure during Gemini API invocation: ${e.message}", e)
            throw Exception("Magic portal error: ${e.message ?: "Check network configuration / secrets."}")
        }
    }

    private fun parseQuestionsJson(rawResponse: String, grade: Int, subject: String, lang: String): List<Question> {
        val topObj = JSONObject(rawResponse)
        val candidates = topObj.getJSONArray("candidates")
        val content = candidates.getJSONObject(0).getJSONObject("content")
        val parts = content.getJSONArray("parts")
        var rawText = parts.getJSONObject(0).getString("text")

        rawText = rawText.trim()
        if (rawText.startsWith("```json")) {
            rawText = rawText.substring(7)
        } else if (rawText.startsWith("```")) {
            rawText = rawText.substring(3)
        }
        if (rawText.endsWith("```")) {
            rawText = rawText.substring(0, rawText.length - 3)
        }
        rawText = rawText.trim()

        val startIndex = rawText.indexOf("[")
        val endIndex = rawText.lastIndexOf("]")
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            rawText = rawText.substring(startIndex, endIndex + 1)
        }

        val array = JSONArray(rawText)
        val questions = mutableListOf<Question>()
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val questionText = obj.getString("question")
            
            val optionsArray = obj.getJSONArray("options")
            val options = mutableListOf<String>()
            for (j in 0 until optionsArray.length()) {
                options.add(optionsArray.getString(j))
            }
            
            val correctAnswer = obj.getInt("correctAnswer")
            val difficulty = obj.optString("difficulty", "medium")
            
            val defaultHintEn = when(subject.lowercase()) {
                "mathematics", "ගණිතය" -> "The ancient mathematician recommends checking your calculations carefully!"
                "science", "විද්‍යාව" -> "Think of the natural forces at play here, adventurer."
                "history", "ඉතිහාසය" -> "Look back into the scrolls of Ceylon history..."
                else -> "A friendly forest pixie whispers the path forward in your ear."
            }
            val defaultHintSi = when(subject.lowercase()) {
                "mathematics", "ගණිතය" -> "සැළකිල්ලෙන් ගණනය කිරීම් පරීක්ෂා කිරීමට ගණිත ශාස්ත්‍රඥයා පවසයි!"
                "science", "විද්‍යාව" -> "මෙහි ක්‍රියාත්මක වන ස්වභාවික බලවේග ගැන සිතන්න, වීරයා."
                "history", "ඉතිහාසය" -> "ලංකා ඉතිහාසයේ පැරණි ලියවිලි දෙස බලන්න..."
                else -> "වන සුරංගනාවියක් ඔබේ කනට රහසින් නිවැරදි මඟ පවසයි."
            }
            val hint = obj.optString("hint", if (lang == "si") defaultHintSi else defaultHintEn)

            questions.add(
                Question(
                    question = questionText,
                    options = options,
                    correctAnswer = correctAnswer,
                    grade = grade,
                    subject = subject,
                    difficulty = difficulty,
                    hint = hint
                )
            )
        }
        return questions
    }

    private fun escapeJson(str: String): String {
        return str.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
    }
}
