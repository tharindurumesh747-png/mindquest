package com.example.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

object PlaySessionManager {
    private const val TAG = "PlaySessionManager"
    private const val PREFS_NAME = "MindQuestSessionPrefs"
    
    // Shown questions keys
    private const val KEY_SHOWN_QUESTION_IDS = "shown_question_ids"
    private const val KEY_LAST_RESET_TIME = "last_reset_time"
    
    // Cache prefix
    private const val PREF_CACHE_PREFIX = "gemini_cache_"
    private const val PREF_TIMESTAMP_PREFIX = "gemini_ts_"

    // In-memory runtime session set for ALL shown questions since app opened (active session)
    private val inMemorySessionShownIds = mutableSetOf<String>()
    
    // In-memory set of recently shown question text strings for final duplicate check
    private val recentlyShownQuestionTexts = mutableSetOf<String>()

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Resets the persistently tracked shown questions every 24 hours.
     * Returns true if reset occurred, false otherwise.
     */
    fun checkDailyReset(context: Context): Boolean {
        val prefs = getPrefs(context)
        val now = System.currentTimeMillis()
        val lastReset = prefs.getLong(KEY_LAST_RESET_TIME, 0L)
        
        // 24 hours in milliseconds = 86400000 ms
        val twentyFourHours = 24L * 60 * 60 * 1000
        if (lastReset == 0L || (now - lastReset) >= twentyFourHours) {
            clearPersistentShownList(context)
            prefs.edit().putLong(KEY_LAST_RESET_TIME, now).apply()
            Log.d(TAG, "Daily reset triggered. History cleared.")
            return true
        }
        return false
    }

    /**
     * Marks a question as shown in the active session and the persistent storage.
     */
    fun markAsShown(context: Context, question: Question) {
        val qid = question.questionId
        inMemorySessionShownIds.add(qid)
        
        val cleanText = question.question.trim().lowercase()
        recentlyShownQuestionTexts.add(cleanText)

        val prefs = getPrefs(context)
        val currentSet = prefs.getStringSet(KEY_SHOWN_QUESTION_IDS, emptySet())?.toMutableSet() ?: mutableSetOf()
        currentSet.add(qid)
        prefs.edit().putStringSet(KEY_SHOWN_QUESTION_IDS, currentSet).apply()
        
        Log.d(TAG, "Marked question shown: ID=$qid, TotalPersistent=${currentSet.size}")
    }

    /**
     * Strictly verifies if a question has been shown in the current play session or persistently within 24 hours.
     * Also uses the visual text matching anti-repeat check.
     */
    fun isQuestionRepeat(context: Context, question: Question): Boolean {
        val qid = question.questionId
        
        // 1. Check in-memory runtime session
        if (inMemorySessionShownIds.contains(qid)) {
            Log.d(TAG, "Anti-repeat [In-Memory ID Match]: Skipped question $qid")
            return true
        }

        // 2. Check persistent SharedPreferences 24H history
        val prefs = getPrefs(context)
        val currentSet = prefs.getStringSet(KEY_SHOWN_QUESTION_IDS, emptySet()) ?: emptySet()
        if (currentSet.contains(qid)) {
            Log.d(TAG, "Anti-repeat [Persistent ID Match]: Skipped question $qid")
            return true
        }

        // 3. Anti-repeat text safety check
        val cleanText = question.question.trim().lowercase()
        if (recentlyShownQuestionTexts.contains(cleanText)) {
            Log.d(TAG, "Anti-repeat [Text Match Shield]: Skipped question with duplicate text: ${question.question}")
            return true
        }

        return false
    }

    /**
     * Clears all session tracking.
     */
    fun clearPersistentShownList(context: Context) {
        getPrefs(context).edit().putStringSet(KEY_SHOWN_QUESTION_IDS, emptySet()).apply()
        inMemorySessionShownIds.clear()
        recentlyShownQuestionTexts.clear()
        Log.d(TAG, "Session tracking cleared.")
    }

    /**
     * Caches Gemini-obtained bonus questions for a specific grade, subject and language.
     */
    fun saveCachedQuestions(context: Context, grade: Int, subject: String, lang: String, questions: List<Question>) {
        val prefs = getPrefs(context)
        val keySuffix = "${grade}_${subject}_${lang}"
        
        try {
            val jsonArray = JSONArray()
            for (q in questions) {
                val obj = JSONObject().apply {
                    put("id", q.questionId)
                    put("question", q.question)
                    put("correctAnswer", q.correctAnswerIndex)
                    put("difficulty", q.difficulty)
                    put("hint", q.hint)
                    
                    val optArr = JSONArray()
                    q.options.forEach { optArr.put(it) }
                    put("options", optArr)
                }
                jsonArray.put(obj)
            }
            
            prefs.edit()
                .putString(PREF_CACHE_PREFIX + keySuffix, jsonArray.toString())
                .putLong(PREF_TIMESTAMP_PREFIX + keySuffix, System.currentTimeMillis())
                .apply()
                
            Log.d(TAG, "Saved ${questions.size} questions to Gemini cache: key=$keySuffix")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to serialize and cache Gemini questions", e)
        }
    }

    /**
     * Retreives is there are valid cached Gemini questions.
     */
    fun getCachedQuestions(context: Context, grade: Int, subject: String, lang: String): List<Question>? {
        val prefs = getPrefs(context)
        val keySuffix = "${grade}_${subject}_${lang}"
        val cacheStr = prefs.getString(PREF_CACHE_PREFIX + keySuffix, null) ?: return null
        
        try {
            val questions = mutableListOf<Question>()
            val array = JSONArray(cacheStr)
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val id = obj.getString("id")
                val questionText = obj.getString("question")
                val correctAnswer = obj.getInt("correctAnswer")
                val difficulty = obj.optString("difficulty", "medium")
                val hint = obj.optString("hint", "")
                
                val optArr = obj.getJSONArray("options")
                val options = mutableListOf<String>()
                for (j in 0 until optArr.length()) {
                    options.add(optArr.getString(j))
                }
                
                questions.add(
                    Question(
                        id = id,
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
        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse cached questions", e)
            return null
        }
    }

    /**
     * Checks if the cached questions exist and are less than 30 days old.
     */
    fun isCacheValid(context: Context, grade: Int, subject: String, lang: String): Boolean {
        val prefs = getPrefs(context)
        val keySuffix = "${grade}_${subject}_${lang}"
        val timestamp = prefs.getLong(PREF_TIMESTAMP_PREFIX + keySuffix, 0L)
        if (timestamp == 0L) return false
        
        val now = System.currentTimeMillis()
        val thirtyDaysMs = 30L * 24 * 60 * 60 * 1000 // 2,592,000,000 milliseconds
        val isValid = (now - timestamp) < thirtyDaysMs
        Log.d(TAG, "Cache age check for $keySuffix: AgeMs=${now - timestamp}, MaxMs=$thirtyDaysMs, IsValid=$isValid")
        return isValid
    }

    /**
     * Clear cache for specific grade, subject, lang
     */
    fun clearCache(context: Context, grade: Int, subject: String, lang: String) {
        val prefs = getPrefs(context)
        val keySuffix = "${grade}_${subject}_${lang}"
        prefs.edit()
            .remove(PREF_CACHE_PREFIX + keySuffix)
            .remove(PREF_TIMESTAMP_PREFIX + keySuffix)
            .apply()
        Log.d(TAG, "Cleared cache for key: $keySuffix")
    }
}
