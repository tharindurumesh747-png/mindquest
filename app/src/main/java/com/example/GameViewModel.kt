package com.example

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Question
import com.example.data.PlaySessionManager
import com.example.data.QuestionBank
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    val stateManager = StateManager()

    // Level progression selection state
    private val _selectedGrade = MutableStateFlow(1)
    val selectedGrade: StateFlow<Int> = _selectedGrade.asStateFlow()

    private val _selectedSubject = MutableStateFlow("Math")
    val selectedSubject: StateFlow<String> = _selectedSubject.asStateFlow()

    private val _selectedLevel = MutableStateFlow(1) // mapped to Stage: 1, 2, or 3
    val selectedLevel: StateFlow<Int> = _selectedLevel.asStateFlow()

    // Current quiz questions pool
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _earnedXp = MutableStateFlow(0)
    val earnedXp: StateFlow<Int> = _earnedXp.asStateFlow()

    private val _lives = MutableStateFlow(3)
    val lives: StateFlow<Int> = _lives.asStateFlow()

    // Screen state flags
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _isOfflineMode = MutableStateFlow(true)
    val isOfflineMode: StateFlow<Boolean> = _isOfflineMode.asStateFlow()

    // Lifeline: 50/50 state
    private val _isFiftyFiftyUsed = MutableStateFlow(false)
    val isFiftyFiftyUsed: StateFlow<Boolean> = _isFiftyFiftyUsed.asStateFlow()

    private val _hintsCount = MutableStateFlow(3)
    val hintsCount: StateFlow<Int> = _hintsCount.asStateFlow()

    private val _disabledOptions = MutableStateFlow<Set<Int>>(emptySet())
    val disabledOptions: StateFlow<Set<Int>> = _disabledOptions.asStateFlow()

    // Option selection visual feedback state
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
        _hintsCount.value = 3
        _disabledOptions.value = emptySet()
        _selectionFeedback.value = null
        _isCorrectFeedback.value = null
        
        SoundManager.playWhoosh()
        loadQuestions(forceRefresh = false)
    }

    fun loadQuestions(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            val context = getApplication<Application>()
            val grade = _selectedGrade.value
            val subject = _selectedSubject.value
            val stage = _selectedLevel.value

            // Perform daily check on persistent question history
            PlaySessionManager.checkDailyReset(context)

            try {
                // Fetch the 10 offline questions for current selection
                val offlineQuestions = QuestionBank.getQuestions(grade, subject, stage)

                if (offlineQuestions.isEmpty()) {
                    _error.value = "No questions loaded. Tap retry to try again."
                    _isLoading.value = false
                    return@launch
                }

                // Shuffling: Every game, fully shuffle question sequences and option arrays
                // Ensuring dual English and Sinhala options index match the same permutation!
                val shuffledFinal = offlineQuestions.shuffled().map { q ->
                    val permutation = (0..3).shuffled()
                    val originalCorrectEn = q.options[q.correctAnswer]
                    
                    val shuffledOptionsEn = permutation.map { q.options[it] }
                    val shuffledOptionsSi = permutation.map { q.optionsSinhala[it] }
                    val newCorrectIndex = shuffledOptionsEn.indexOf(originalCorrectEn)
                    
                    q.copy(
                        options = shuffledOptionsEn,
                        optionsSinhala = shuffledOptionsSi,
                        correctAnswer = newCorrectIndex
                    )
                }

                _questions.value = shuffledFinal
                _isOfflineMode.value = true
                _isLoading.value = false
                
                // Track first question as shown as soon as we start
                if (shuffledFinal.isNotEmpty()) {
                    PlaySessionManager.markAsShown(context, shuffledFinal[0])
                }
                
                startTimerLimit()
            } catch (e: Exception) {
                Log.e("GameViewModel", "Critical failure during question system loading: ${e.message}", e)
                _error.value = "Failed to load offline questions: ${e.message} \nTap retry to try again."
                _isLoading.value = false
            }
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
        _lives.value = (_lives.value - 1).coerceAtLeast(0)
        _isCorrectFeedback.value = false
        _selectionFeedback.value = -1 // No selection feedback index
        SoundManager.playWrong()
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

        // Play synthesized sound effect & award XP on correct choice
        if (isCorrect) {
            SoundManager.playCorrect()
            _score.value += 1
            _earnedXp.value += 20 // 20 XP per correct question
            stateManager.addXp(20)
        } else {
            SoundManager.playWrong()
            _lives.value = (_lives.value - 1).coerceAtLeast(0)
        }
    }

    fun goToNextQuestion(onQuizFinished: () -> Unit) {
        _selectionFeedback.value = null
        _isCorrectFeedback.value = null
        _disabledOptions.value = emptySet()

        if (_lives.value <= 0) {
            onQuizFinished()
        } else {
            val currentList = _questions.value
            val nextIdx = _currentQuestionIndex.value + 1
            
            if (nextIdx < currentList.size) {
                _currentQuestionIndex.value = nextIdx
                PlaySessionManager.markAsShown(getApplication(), currentList[nextIdx])
                SoundManager.playWhoosh()
                startTimerLimit()
            } else {
                stateManager.completeQuiz()
                
                // Save progress for stage completion! (Save stars using SharedPreferences)
                val context = getApplication<Application>()
                PlaySessionManager.markStageCompleted(context, _selectedGrade.value, _selectedSubject.value, _selectedLevel.value, _score.value)
                
                // Check performance on result screen
                val passed = _score.value >= (currentList.size * 0.5f).toInt()
                if (passed) {
                    SoundManager.playVictoryFanfare()
                } else {
                    SoundManager.playWrong()
                }
                
                if (_selectedGrade.value < 10 && _score.value >= 4) {
                    stateManager.unlockGrade(_selectedGrade.value + 1)
                }
                onQuizFinished()
            }
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

    fun useHint(): Boolean {
        if (_hintsCount.value > 0) {
            _hintsCount.value -= 1
            _earnedXp.value -= 5
            return true
        }
        return false
    }
}
