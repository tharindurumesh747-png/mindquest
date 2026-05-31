package com.example

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateManager {
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

    fun switchTheme(themeName: String) {
        _theme.value = themeName
    }

    fun switchLanguage(lang: String) {
        _language.value = lang
    }

    fun addXp(amount: Int) {
        _totalXp.value += amount
        // Level up algorithm (100 XP per level)
        val newLevel = (_totalXp.value / 100) + 1
        if (newLevel > _playerLevel.value) {
            _playerLevel.value = newLevel
        }
    }

    fun completeQuiz() {
        _completedQuizzes.value += 1
    }

    fun unlockGrade(grade: Int) {
        if (!_unlockedGrades.value.contains(grade)) {
            _unlockedGrades.value = _unlockedGrades.value + grade
        }
    }
}
