package com.example.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

object PlaySessionManager {
    private const val TAG = "PlaySessionManager"
    private const val PREFS_NAME = "MindQuestSessionPrefs"
    
    // Shown questions keys
    private const val KEY_SHOWN_QUESTION_IDS = "shown_question_ids"
    private const val KEY_LAST_RESET_TIME = "last_reset_time"

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
        val qid = question.id
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
     */
    fun isQuestionRepeat(context: Context, question: Question): Boolean {
        val qid = question.id
        
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

    // ==========================================
    // STAGE PROGRESSION SYSTEM (Offline Persistence)
    // ==========================================

    /**
     * Retrieve star count rating for a completed stage (returns -1 if not completed)
     */
    fun getStageStars(context: Context, grade: Int, subject: String, stage: Int): Int {
        val prefs = getPrefs(context)
        val normSubj = subject.lowercase().trim()
        return prefs.getInt("stars_${grade}_${normSubj}_${stage}", -1)
    }

    /**
     * Checks if a specific stage has ever been completed (with any rating)
     */
    fun isStageCompleted(context: Context, grade: Int, subject: String, stage: Int): Boolean {
        val prefs = getPrefs(context)
        val normSubj = subject.lowercase().trim()
        return prefs.getBoolean("completed_${grade}_${normSubj}_${stage}", false)
    }

    /**
     * Mark a stage as completed, compute stars based on score, and save to SharedPreferences
     * Star Rules:
     * - 10/10 = 3 stars
     * - 7-9 = 2 stars
     * - 4-6 = 1 star
     * - below 4 = 0 stars
     */
    fun markStageCompleted(context: Context, grade: Int, subject: String, stage: Int, score: Int) {
        val prefs = getPrefs(context)
        val normSubj = subject.lowercase().trim()
        
        val stars = when (score) {
            10 -> 3
            in 7..9 -> 2
            in 4..6 -> 1
            else -> 0
        }
        
        val currentStars = getStageStars(context, grade, subject, stage)
        if (stars > currentStars) {
            prefs.edit().putInt("stars_${grade}_${normSubj}_${stage}", stars).apply()
        }
        prefs.edit().putBoolean("completed_${grade}_${normSubj}_${stage}", true).apply()
        Log.d(TAG, "Progress Saved: Grade=$grade, Subject=$normSubj, Stage=$stage, Score=$score, Stars=$stars")
    }

    /**
     * Checks if a stage is unlocked:
     * - Stage I is unlocked by default
     * - Stage II requires Stage I completed
     * - Stage III requires Stage II completed
     */
    fun isStageUnlocked(context: Context, grade: Int, subject: String, stage: Int): Boolean {
        if (stage == 1) return true
        if (stage == 2) return isStageCompleted(context, grade, subject, 1)
        if (stage == 3) return isStageCompleted(context, grade, subject, 2)
        return false
    }
}
