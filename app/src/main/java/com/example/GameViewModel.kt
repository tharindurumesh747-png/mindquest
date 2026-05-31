package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Question
import com.example.data.QuestionPool
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class GameViewModel : ViewModel() {
    val stateManager = StateManager()

    // Level progression selection state
    private val _selectedGrade = MutableStateFlow(1)
    val selectedGrade: StateFlow<Int> = _selectedGrade.asStateFlow()

    private val _selectedSubject = MutableStateFlow("Math")
    val selectedSubject: StateFlow<String> = _selectedSubject.asStateFlow()

    private val _selectedLevel = MutableStateFlow(1)
    val selectedLevel: StateFlow<Int> = _selectedLevel.asStateFlow()

    // Active Quiz states
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _earnedXp = MutableStateFlow(0)
    val earnedXp: StateFlow<Int> = _earnedXp.asStateFlow()

    private val _lives = MutableStateFlow(3)
    val lives: StateFlow<Int> = _lives.asStateFlow()

    // Hint and visual Feedback State
    private val _isFiftyFiftyUsed = MutableStateFlow(false)
    val isFiftyFiftyUsed: StateFlow<Boolean> = _isFiftyFiftyUsed.asStateFlow()

    private val _disabledOptions = MutableStateFlow<Set<Int>>(emptySet())
    val disabledOptions: StateFlow<Set<Int>> = _disabledOptions.asStateFlow()

    private val _selectionFeedback = MutableStateFlow<Int?>(null)
    val selectionFeedback: StateFlow<Int?> = _selectionFeedback.asStateFlow()

    private val _isCorrectFeedback = MutableStateFlow<Boolean?>(null)
    val isCorrectFeedback: StateFlow<Boolean?> = _isCorrectFeedback.asStateFlow()

    // Timer logic
    private val _timerSeconds = MutableStateFlow(30)
    val timerSeconds: StateFlow<Int> = _timerSeconds.asStateFlow()

    private val _isTimeRunning = MutableStateFlow(false)
    val isTimeRunning: StateFlow<Boolean> = _isTimeRunning.asStateFlow()

    private var timerJob: Job? = null

    // OkHttp Client Configuration for direct REST API
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(45, TimeUnit.SECONDS)
        .readTimeout(45, TimeUnit.SECONDS)
        .build()

    fun selectGrade(grade: Int) {
        _selectedGrade.value = grade
    }

    fun selectSubject(subject: String) {
        _selectedSubject.value = subject
    }

    fun selectLevel(level: Int) {
        _selectedLevel.value = level
    }

    fun startQuiz() {
        stopTimer()
        _currentQuestionIndex.value = 0
        _score.value = 0
        _earnedXp.value = 0
        _lives.value = 3
        _isFiftyFiftyUsed.value = false
        _disabledOptions.value = emptySet()
        _selectionFeedback.value = null
        _isCorrectFeedback.value = null
        
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            val lang = stateManager.language.value
            val grade = _selectedGrade.value
            val subject = _selectedSubject.value
            
            // Build offline fallback beforehand
            val offlineFallback = QuestionPool.getOfflineQuestions(grade, subject, lang)
            
            val apiKey = BuildConfig.GEMINI_API_KEY
            if (apiKey.isEmpty() || apiKey == "GEMINI_API_KEY_DEFAULT_VALUE") {
                // Instantly swap to offline questions as graceful check
                delay(1000) // Aesthetic delay for retro loader feel
                _questions.value = offlineFallback
                _isLoading.value = false
                startTimerLimit()
                return@launch
            }

            try {
                val responseJson = fetchQuestionsFromGemini(apiKey, grade, subject, lang)
                val parsedList = parseGeminiResponse(responseJson)
                if (parsedList.isNotEmpty()) {
                    _questions.value = parsedList
                } else {
                    _questions.value = offlineFallback
                }
            } catch (e: Exception) {
                // Fallback to offline gracefully
                _questions.value = offlineFallback
            } finally {
                _isLoading.value = false
                startTimerLimit()
            }
        }
    }

    private suspend fun fetchQuestionsFromGemini(apiKey: String, grade: Int, subject: String, lang: String): String {
        val systemPrompt = "You are MindQuest, an interactive fantasy-RPG quiz game master. You write educational adventure questions."
        val fullPrompt = """
            Generate 5 multiple choice questions suitable for Grade $grade students on the subject of '$subject' in ${if (lang == "si") "Sinhala language" else "English language"}.
            Infuse minor fantasy RPG flavor (e.g. mentions of magical chambers, dragon eggs, knights, enchanted scrolls, castles).
            
            Return ONLY a raw valid JSON array, do not include any markdown format (no ```json).
            Each question object must contain precisely the following:
            - "question": string
            - "options": list of 4 strings
            - "answerIndex": integer (0 to 3)
            - "hint": string (helpful RPG wizard clue)
        """.trimIndent()

        val escapedPrompt = escapeJson(fullPrompt)
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
                "responseMimeType": "application/json"
              }
            }
        """.trimIndent()

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = requestJson.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=$apiKey")
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("HTTP Error: ${response.code}")
            }
            val body = response.body?.string() ?: throw Exception("Empty response body")
            return body
        }
    }

    private fun parseGeminiResponse(rawBody: String): List<Question> {
        // Parse the top-level generateContent response JSON
        val topObj = JSONObject(rawBody)
        val candidates = topObj.getJSONArray("candidates")
        val content = candidates.getJSONObject(0).getJSONObject("content")
        val parts = content.getJSONArray("parts")
        val rawText = parts.getJSONObject(0).getString("text")

        // Extraction to handle raw text JSON block
        val startIndex = rawText.indexOf('[')
        val endIndex = rawText.lastIndexOf(']')
        if (startIndex == -1 || endIndex == -1 || endIndex < startIndex) {
            throw Exception("No JSON array returned in textual payload")
        }
        val jsonArrayStr = rawText.substring(startIndex, endIndex + 1)
        val array = JSONArray(jsonArrayStr)
        val results = mutableListOf<Question>()

        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val questionText = obj.getString("question")
            val optionsArray = obj.getJSONArray("options")
            val optionsList = mutableListOf<String>()
            for (j in 0 until optionsArray.length()) {
                optionsList.add(optionsArray.getString(j))
            }
            val correctIdx = obj.getInt("answerIndex")
            val quizHint = obj.optString("hint", "The wizard points towards the stars.")
            
            results.add(Question(questionText, optionsList, correctIdx, quizHint))
        }
        return results
    }

    private fun escapeJson(str: String): String {
        return str.replace("\\", "\\\\")
            .replace("\"", "\\\"")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\t", "\\t")
    }

    // Timer loop control
    private fun startTimerLimit() {
        stopTimer()
        _timerSeconds.value = 30
        _isTimeRunning.value = true
        
        timerJob = viewModelScope.launch {
            while (_timerSeconds.value > 0 && _isTimeRunning.value) {
                delay(1000)
                if (_isTimeRunning.value) {
                    _timerSeconds.value -= 1
                }
            }
            if (_timerSeconds.value == 0 && _isTimeRunning.value) {
                // Time's up is counted as an incorrect answer (loss of 1 life)
                onTimerExpired()
            }
        }
    }

    fun stopTimer() {
        _isTimeRunning.value = false
        timerJob?.cancel()
    }

    private fun onTimerExpired() {
        viewModelScope.launch {
            _lives.value = (_lives.value - 1).coerceAtLeast(0)
            _isCorrectFeedback.value = false
            _selectionFeedback.value = -1 // No selection feedback index
            
            delay(1500)
            _selectionFeedback.value = null
            _isCorrectFeedback.value = null
            _disabledOptions.value = emptySet()
            
            goToNextQuestionOrFinish()
        }
    }

    // Submitting selection for current question
    fun submitAnswer(selectedIndex: Int, onQuizFinished: () -> Unit) {
        val currentList = _questions.value
        val currentIndex = _currentQuestionIndex.value
        if (currentList.isEmpty() || currentIndex >= currentList.size || _lives.value <= 0) return

        val correctIndex = currentList[currentIndex].correctAnswerIndex
        val isCorrect = selectedIndex == correctIndex

        stopTimer()
        _selectionFeedback.value = selectedIndex
        _isCorrectFeedback.value = isCorrect

        viewModelScope.launch {
            if (isCorrect) {
                _score.value += 1
                _earnedXp.value += 20 // 20 XP per correct question
                stateManager.addXp(20)
            } else {
                _lives.value = (_lives.value - 1).coerceAtLeast(0)
            }

            delay(1500) // Allow reading state in dynamic ui colors
            
            _selectionFeedback.value = null
            _isCorrectFeedback.value = null
            _disabledOptions.value = emptySet()

            if (_lives.value <= 0) {
                onQuizFinished()
            } else {
                goToNextQuestionOrFinish(onQuizFinished)
            }
        }
    }

    private fun goToNextQuestionOrFinish(onQuizFinished: (() -> Unit)? = null) {
        val currentList = _questions.value
        val nextIdx = _currentQuestionIndex.value + 1
        
        if (nextIdx < currentList.size) {
            _currentQuestionIndex.value = nextIdx
            startTimerLimit()
        } else {
            stateManager.completeQuiz()
            if (_selectedGrade.value < 10 && _score.value >= 3) {
                stateManager.unlockGrade(_selectedGrade.value + 1)
            }
            onQuizFinished?.invoke()
        }
    }

    // Hint: 50-50 logic - leaves correct answer and 1 random incorrect answer
    fun useFiftyFifty() {
        val currentList = _questions.value
        val currentIndex = _currentQuestionIndex.value
        if (currentList.isEmpty() || currentIndex >= currentList.size || _isFiftyFiftyUsed.value) return

        val correctIndex = currentList[currentIndex].correctAnswerIndex
        val allIndices = (0..3).toMutableList()
        allIndices.remove(correctIndex)
        
        // Randomly choose 1 incorrect to keep, hide the other 2
        allIndices.shuffle()
        val toDisable = allIndices.take(2).toSet()
        
        _disabledOptions.value = toDisable
        _isFiftyFiftyUsed.value = true
    }
}
