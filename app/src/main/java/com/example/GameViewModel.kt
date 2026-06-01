package com.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Question
import com.example.data.QuestionBank
import com.example.data.SoundSettings
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Random

class GameViewModel : ViewModel() {

    // Language / Stage selectors
    private val _language = MutableStateFlow("English")
    val language: StateFlow<String> = _language.asStateFlow()

    private val _currentGrade = MutableStateFlow(3)
    val currentGrade: StateFlow<Int> = _currentGrade.asStateFlow()

    private val _currentSubject = MutableStateFlow("Science")
    val currentSubject: StateFlow<String> = _currentSubject.asStateFlow()

    private val _currentStage = MutableStateFlow(1)
    val currentStage: StateFlow<Int> = _currentStage.asStateFlow()

    // Question lists and indexing
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    // Stats
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score.asStateFlow()

    private val _lives = MutableStateFlow(3)
    val lives: StateFlow<Int> = _lives.asStateFlow()

    private val _currentStreak = MutableStateFlow(0)
    val currentStreak: StateFlow<Int> = _currentStreak.asStateFlow()

    private val _bestStreak = MutableStateFlow(0)
    val bestStreak: StateFlow<Int> = _bestStreak.asStateFlow()

    private val _totalTimeSpentSec = MutableStateFlow(0)
    val totalTimeSpentSec: StateFlow<Int> = _totalTimeSpentSec.asStateFlow()

    private val _ticksRemaining = MutableStateFlow(30)
    val ticksRemaining: StateFlow<Int> = _ticksRemaining.asStateFlow()

    private val _correctAnswersCount = MutableStateFlow(0)
    val correctAnswersCount: StateFlow<Int> = _correctAnswersCount.asStateFlow()

    // Helpers / RPG Spells
    private val _hintsCount = MutableStateFlow(3)
    val hintsCount: StateFlow<Int> = _hintsCount.asStateFlow()

    private val _isFiftyFiftyUsed = MutableStateFlow(false)
    val isFiftyFiftyUsed: StateFlow<Boolean> = _isFiftyFiftyUsed.asStateFlow()

    private val _disabledOptions = MutableStateFlow<Set<Int>>(emptySet())
    val disabledOptions: StateFlow<Set<Int>> = _disabledOptions.asStateFlow()

    // Real-time animation visual flow triggers
    private val _floatingScoreAnimation = MutableStateFlow<String?>(null)
    val floatingScoreAnimation: StateFlow<String?> = _floatingScoreAnimation.asStateFlow()

    private val _comboTextAnimation = MutableStateFlow<String?>(null)
    val comboTextAnimation: StateFlow<String?> = _comboTextAnimation.asStateFlow()

    private val _encouragingMessage = MutableStateFlow<String?>(null)
    val encouragingMessage: StateFlow<String?> = _encouragingMessage.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _isCorrectFeedback = MutableStateFlow<Boolean?>(null)
    val isCorrectFeedback: StateFlow<Boolean?> = _isCorrectFeedback.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    private var timerJob: Job? = null

    init {
        // Initially set a basic set of questions
        loadQuestions()
    }

    fun setLanguage(lang: String) {
        _language.value = lang
    }

    fun selectGrade(g: Int) {
        _currentGrade.value = g
        _currentStage.value = 1
        loadQuestions()
    }

    fun selectSubject(subj: String) {
        _currentSubject.value = subj
        _currentStage.value = 1
        loadQuestions()
    }

    fun setStage(stg: Int) {
        _currentStage.value = stg
        loadQuestions()
    }

    fun loadQuestions() {
        val qList = QuestionBank.getQuestionsFor(
            _currentGrade.value,
            _currentSubject.value,
            _currentStage.value
        )
        _questions.value = qList
        resetQuizState()
    }

    fun resetQuizState() {
        _currentIndex.value = 0
        _score.value = 0
        _lives.value = 3
        _currentStreak.value = 0
        _bestStreak.value = 0
        _totalTimeSpentSec.value = 0
        _correctAnswersCount.value = 0
        _hintsCount.value = 3
        _isFiftyFiftyUsed.value = false
        _disabledOptions.value = emptySet()
        _floatingScoreAnimation.value = null
        _comboTextAnimation.value = null
        _encouragingMessage.value = null
        _selectedAnswerIndex.value = null
        _isCorrectFeedback.value = null
        _quizFinished.value = false
        cancelTimer()
        startTimer()
    }

    // ═══════════════════════════════════════
    // TIMER PRESSURE MANAGEMENT
    // ═══════════════════════════════════════

    fun startTimer() {
        cancelTimer()
        _ticksRemaining.value = 30
        timerJob = viewModelScope.launch {
            while (_ticksRemaining.value > 0 && !_quizFinished.value) {
                delay(1000)
                if (_ticksRemaining.value > 0) {
                    _ticksRemaining.value -= 1
                    _totalTimeSpentSec.value += 1
                    
                    // Play dynamic ticking sound with increasing pitch
                    if (_ticksRemaining.value in 1..3) {
                        SoundManager.playTimerTick(3) // Level 3 urgent beep
                    } else if (_ticksRemaining.value in 4..7) {
                        SoundManager.playTimerTick(2) // Level 2 fast tick
                    } else if (_ticksRemaining.value in 8..15) {
                        SoundManager.playTimerTick(1) // Level 1 normal tick
                    }
                }
            }
            if (_ticksRemaining.value == 0) {
                onTimeOut()
            }
        }
    }

    fun cancelTimer() {
        timerJob?.cancel()
        timerJob = null
    }

    private fun onTimeOut() {
        _selectedAnswerIndex.value = -1 // indicates timeout
        _isCorrectFeedback.value = false
        SoundManager.playWrong()

        val isSin = _language.value == "Sinhala"
        _encouragingMessage.value = getRandomEncouragement(false, isSin)
        _floatingScoreAnimation.value = "-5"
        adjustScore(-5)

        _currentStreak.value = 0
        _lives.value = (_lives.value - 1).coerceAtLeast(0)

        viewModelScope.launch {
            delay(1500)
            _floatingScoreAnimation.value = null
            _encouragingMessage.value = null
            advanceQuestion()
        }
    }

    // ═══════════════════════════════════════
    // ANSWER SUBMISSION (BUG 6)
    // ═══════════════════════════════════════

    fun submitAnswer(optionIndex: Int) {
        if (_selectedAnswerIndex.value != null || _quizFinished.value) return
        cancelTimer()

        val currentQ = _questions.value.getOrNull(_currentIndex.value) ?: return
        val isCorrect = optionIndex == currentQ.correctAnswerIndex
        val isSin = _language.value == "Sinhala"

        _selectedAnswerIndex.value = optionIndex
        _isCorrectFeedback.value = isCorrect

        if (isCorrect) {
            _correctAnswersCount.value += 1
            _currentStreak.value += 1
            if (_currentStreak.value > _bestStreak.value) {
                _bestStreak.value = _currentStreak.value
            }

            // Play correct fanfare sound
            SoundManager.playCorrect()

            // Calculate Base points
            var earnedPoints = 10
            var animationVal = "+10"

            // Timer Speed bonus points
            val secondsElapsed = 30 - _ticksRemaining.value
            if (secondsElapsed < 5) {
                earnedPoints += 5
                animationVal += "\n+5 Fast Bonus!"
            } else if (secondsElapsed < 10) {
                earnedPoints += 3
                animationVal += "\n+3 Fast Bonus!"
            } else if (secondsElapsed < 15) {
                earnedPoints += 1
                animationVal += "\n+1 Fast Bonus!"
            }

            // Combo System checks
            val streakVal = _currentStreak.value
            if (streakVal == 3) {
                earnedPoints += 10
                _comboTextAnimation.value = if (isSin) "3x සංයෝජනය! 🔥" else "3x Combo! 🔥"
                animationVal += "\n+10 Combo!"
                SoundManager.playComboSound(3)
            } else if (streakVal == 5) {
                earnedPoints += 20
                _comboTextAnimation.value = if (isSin) "5x සංයෝජනය! ⚡" else "5x Combo! ⚡"
                animationVal += "\n+20 Combo!"
                SoundManager.playComboSound(5)
            } else if (streakVal == 10) {
                earnedPoints += 50
                _comboTextAnimation.value = if (isSin) "විශිෂ්ටයි! 👑" else "LEGENDARY! 👑"
                animationVal += "\n+50 Combo!"
                SoundManager.playComboSound(10)
            }

            adjustScore(earnedPoints)
            _floatingScoreAnimation.value = animationVal
            _encouragingMessage.value = getRandomEncouragement(true, isSin)

        } else {
            // Wrong answer
            _currentStreak.value = 0
            SoundManager.playWrong()

            adjustScore(-5)
            _floatingScoreAnimation.value = "-5"
            _encouragingMessage.value = getRandomEncouragement(false, isSin)
            _lives.value = (_lives.value - 1).coerceAtLeast(0)
        }

        viewModelScope.launch {
            delay(2500)
            _floatingScoreAnimation.value = null
            _comboTextAnimation.value = null
            _encouragingMessage.value = null
            advanceQuestion()
        }
    }

    private fun adjustScore(pts: Int) {
        val newScore = _score.value + pts
        _score.value = newScore.coerceAtLeast(0)
    }

    private fun advanceQuestion() {
        _selectedAnswerIndex.value = null
        _isCorrectFeedback.value = null
        _disabledOptions.value = emptySet()

        val nextIndex = _currentIndex.value + 1
        val listSize = _questions.value.size
        
        if (nextIndex < listSize && _lives.value > 0) {
            _currentIndex.value = nextIndex
            startTimer()
        } else {
            // Stage complete or game over
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        _quizFinished.value = true
        cancelTimer()
        if (_lives.value > 0 && _correctAnswersCount.value >= (_questions.value.size / 2.0)) {
            SoundManager.playStageComplete()
        } else {
            SoundManager.playGameOver()
        }
    }

    // ═══════════════════════════════════════
    // ENCOURAGING MESSAGES GENERATOR
    // ═══════════════════════════════════════

    private fun getRandomEncouragement(isCorrect: Boolean, isSinhala: Boolean): String {
        val rand = Random()
        if (isCorrect) {
            val eng = listOf(
                "Excellent! 🌟",
                "Amazing! Keep going! 🚀",
                "Brilliant! You're on fire! 🔥",
                "Perfect! 💯",
                "Outstanding! ⭐"
            )
            val sin = listOf(
                "විශිෂ්ටයි! 🌟",
                "පුදුමාකාරයි! ඉදිරියටම යමු! 🚀",
                "පණ්ඩිතයි! දිගටම කරගෙන යන්න! 🔥",
                "පරිපූර්ණයි! 💯",
                "සුවිශේෂීයි! ⭐"
            )
            return if (isSinhala) sin[rand.nextInt(sin.size)] else eng[rand.nextInt(eng.size)]
        } else {
            val eng = listOf(
                "Don't give up! Try again! 💪",
                "Almost there! Keep trying! 🎯",
                "You can do it! 🌟",
                "Learn from mistakes! 📚"
            )
            val sin = listOf(
                "අත්හරින්න එපා! නැවත උත්සාහ කරන්න! 💪",
                "ලඟ ලඟම එනවා! දිගටම උත්සාහ කරන්න! 🎯",
                "ඔබට එය කළ හැකියි! 🌟",
                "වැරදිවලින් ඉගෙන ගනිමු! 📚"
            )
            return if (isSinhala) sin[rand.nextInt(sin.size)] else eng[rand.nextInt(eng.size)]
        }
    }

    // ═══════════════════════════════════════
    // HELPERS & SPELLS (50/50, Hint)
    // ═══════════════════════════════════════

    fun useFiftyFifty() {
        if (_isFiftyFiftyUsed.value || _selectedAnswerIndex.value != null || _quizFinished.value) return
        val currentQ = _questions.value.getOrNull(_currentIndex.value) ?: return

        val correctIndex = currentQ.correctAnswerIndex
        val wrongIndices = (0..3).filter { it != correctIndex }
        
        // Disable two random incorrect indices
        val rand = Random()
        val pickedWrong = wrongIndices.shuffled(rand).take(2).toSet()

        _disabledOptions.value = pickedWrong
        _isFiftyFiftyUsed.value = true
        SoundManager.playLevelUp() // magical sparkle sounds
    }

    fun useHint(): Boolean {
        if (_hintsCount.value > 0) {
            _hintsCount.value -= 1
            adjustScore(-5)
            // Trigger floating deduction note
            _floatingScoreAnimation.value = "-5 (Hint / ඉඟිය)"
            SoundManager.playHintUsed()
            viewModelScope.launch {
                delay(1200)
                if (_floatingScoreAnimation.value == "-5 (Hint / ඉඟිය)") {
                    _floatingScoreAnimation.value = null
                }
            }
            return true
        }
        return false
    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }
}
