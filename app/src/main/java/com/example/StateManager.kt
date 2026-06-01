package com.example

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateManager {
    private var sharedPrefs: android.content.SharedPreferences? = null
    var onLanguageChanged: (() -> Unit)? = null

    // Current realm selection (theme)
    private val _theme = MutableStateFlow("Cosmic Blue")
    val theme: StateFlow<String> = _theme.asStateFlow()

    // Language setting ("en" for English, "si" for Sinhala)
    private val _language = MutableStateFlow("en")
    val language: StateFlow<String> = _language.asStateFlow()

    // RPG XP stats & progression
    private val _totalXp = MutableStateFlow(0)
    val totalXp: StateFlow<Int> = _totalXp.asStateFlow()

    private val _playerLevel = MutableStateFlow(1)
    val playerLevel: StateFlow<Int> = _playerLevel.asStateFlow()

    private val _completedQuizzes = MutableStateFlow(0)
    val completedQuizzes: StateFlow<Int> = _completedQuizzes.asStateFlow()

    // RPG Grade unlocks / levels completed tracker
    private val _unlockedGrades = MutableStateFlow(setOf(1, 2, 3))
    val unlockedGrades: StateFlow<Set<Int>> = _unlockedGrades.asStateFlow()

    fun init(context: android.content.Context) {
        val prefs = context.getSharedPreferences("mindquest_prefs", android.content.Context.MODE_PRIVATE)
        sharedPrefs = prefs
        _language.value = prefs.getString("language", "en") ?: "en"
        _theme.value = prefs.getString("theme", "Cosmic Blue") ?: "Cosmic Blue"
        _totalXp.value = prefs.getInt("total_xp", 0)
        _playerLevel.value = prefs.getInt("player_level", 1)
        _completedQuizzes.value = prefs.getInt("completed_quizzes", 0)
        
        val gradeString = prefs.getString("unlocked_grades", "1,2,3") ?: "1,2,3"
        val grades = gradeString.split(",").mapNotNull { it.toIntOrNull() }.toSet()
        _unlockedGrades.value = grades.ifEmpty { setOf(1, 2, 3) }
    }

    fun switchTheme(themeName: String) {
        _theme.value = themeName
        sharedPrefs?.edit()?.putString("theme", themeName)?.apply()
    }

    fun switchLanguage(lang: String, context: android.content.Context? = null) {
        val oldLang = _language.value
        if (oldLang != lang) {
            _language.value = lang
            sharedPrefs?.edit()?.putString("language", lang)?.apply()
            
            if (context != null) {
                val msg = if (lang == "si") {
                    "Switching to Sinhala..."
                } else {
                    "ඉංග්‍රීසියට මාරු වෙමින්..."
                }
                android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_SHORT).show()
            }
            onLanguageChanged?.invoke()
        }
    }

    fun addXp(amount: Int) {
        _totalXp.value += amount
        val newLevel = (_totalXp.value / 100) + 1
        if (newLevel > _playerLevel.value) {
            _playerLevel.value = newLevel
            sharedPrefs?.edit()?.putInt("player_level", newLevel)?.apply()
        }
        sharedPrefs?.edit()?.putInt("total_xp", _totalXp.value)?.apply()
    }

    fun completeQuiz() {
        _completedQuizzes.value += 1
        sharedPrefs?.edit()?.putInt("completed_quizzes", _completedQuizzes.value)?.apply()
    }

    fun unlockGrade(grade: Int) {
        if (!_unlockedGrades.value.contains(grade)) {
            val updated = _unlockedGrades.value + grade
            _unlockedGrades.value = updated
            sharedPrefs?.edit()?.putString("unlocked_grades", updated.joinToString(","))?.apply()
        }
    }
}
