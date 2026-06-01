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

    // Cache of generated questions by key: "grade_subject_lang"
    private val questionsCache = mutableMapOf<String, MutableList<Question>>()
    // Track asked questions in this session by key: "grade_subject_lang" to prevent repeats
    private val shownQuestionsHistory = mutableMapOf<String, MutableSet<String>>()

    init {
        stateManager.onLanguageChanged = {
            clearQuestionCache()
        }
    }

    fun clearQuestionCache() {
        questionsCache.clear()
        shownQuestionsHistory.clear()
    }

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

    fun loadQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            val lang = stateManager.language.value
            val grade = _selectedGrade.value
            val subject = _selectedSubject.value
            val cacheKey = "${grade}_${subject}_${lang}"
            
            try {
                // Ensure a shownQuestions list exists for this key
                val shownSet = shownQuestionsHistory.getOrPut(cacheKey) { mutableSetOf() }
                
                // Get list belonging to key in our pool
                val pool = questionsCache.getOrPut(cacheKey) { mutableListOf() }
                
                // Filter unasked questions
                var unshown = pool.filter { !shownSet.contains(it.question) }
                
                if (unshown.size < 20) {
                    // Fetch 20 fresh ones from Gemini
                    val fresh = com.example.data.QuestionGenerator.generateQuestions(grade, subject, lang)
                    pool.addAll(fresh)
                    
                    // Filter again with new added pool
                    unshown = pool.filter { !shownSet.contains(it.question) }
                }
                
                // We must take exactly 20 questions
                val selectedQuestions = if (unshown.size >= 20) {
                    unshown.shuffled().take(20)
                } else {
                    // If we still can't satisfy 20 due to duplicates or API constraints, 
                    // clear the history for this session, shuffle the entire pool, and use them
                    shownSet.clear()
                    if (pool.size >= 20) {
                        pool.shuffled().take(20)
                    } else {
                        // Fallback to offline questions to fill up 20 questions if pool is somehow smaller than 20
                        val offline = QuestionPool.getOfflineQuestions(grade, subject, lang).shuffled()
                        val combined = (pool + offline).distinctBy { it.question }
                        combined.take(20)
                    }
                }
                
                // Track these questions as shown in this session
                shownSet.addAll(selectedQuestions.map { it.question })
                
                // Set the questions state flow
                _questions.value = selectedQuestions
                _isLoading.value = false
                startTimerLimit()
            } catch (e: Exception) {
                _error.value = e.message ?: "Failed to invoke wizard scroll power. Please check network/secrets configuration."
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

        // Play synthesized sound effect
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
}
