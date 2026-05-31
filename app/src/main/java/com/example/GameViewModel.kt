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
        
        SoundManager.playWhoosh()
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            val lang = stateManager.language.value
            val grade = _selectedGrade.value
            val subject = _selectedSubject.value
            
            delay(800) // Aesthetic delay for retro loader feel
            
            val offlineQuestions = QuestionPool.getOfflineQuestions(grade, subject, lang).toMutableList()
            offlineQuestions.shuffle() // Never show same order
            
            // Weave in exactly 5 unique random bonus questions to satisfy "appear at any time during gameplay" and "200+ bonus questions"
            val bonusPool = QuestionPool.getBonusQuestions(lang).shuffled()
            val injectCount = 5
            for (i in 0 until injectCount) {
                if (i < bonusPool.size) {
                    val insertPos = (0..offlineQuestions.size).random()
                    offlineQuestions.add(insertPos, bonusPool[i])
                }
            }
            
            _questions.value = offlineQuestions
            _isLoading.value = false
            startTimerLimit()
        }
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
            
            SoundManager.playWrong()
            
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

        // Play synthesized sound effect
        if (isCorrect) {
            SoundManager.playCorrect()
        } else {
            SoundManager.playWrong()
        }

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
            SoundManager.playWhoosh()
            startTimerLimit()
        } else {
            stateManager.completeQuiz()
            
            // Check performance on result screen
            val passed = _score.value >= (currentList.size * 0.5f).toInt()
            if (passed) {
                SoundManager.playVictoryFanfare()
            } else {
                SoundManager.playWrong()
            }
            
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
