package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.GameViewModel
import com.example.SoundManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.GlassCard
import com.example.ui.components.HeartsRepresentationBar
import com.example.ui.components.NeonButton
import com.example.ui.theme.FantasyTheme

@Composable
fun QuizScreen(
    viewModel: GameViewModel,
    theme: FantasyTheme,
    onQuizFinished: () -> Unit,
    onBack: () -> Unit
) {
    val currentLang by viewModel.stateManager.language.collectAsState()
    val isSinhala = currentLang == "si"

    // Collect flow states from the offline repository-driven ViewModel
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    val timerSeconds by viewModel.timerSeconds.collectAsState()
    val lives by viewModel.lives.collectAsState()
    val selectionFeedback by viewModel.selectionFeedback.collectAsState()
    val isCorrectFeedback by viewModel.isCorrectFeedback.collectAsState()
    val isFiftyFiftyUsed by viewModel.isFiftyFiftyUsed.collectAsState()
    val disabledOptions by viewModel.disabledOptions.collectAsState()

    // Local Interactive workflow states
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isSubmitted by remember { mutableStateOf(false) }
    var showSpellDialog by remember { mutableStateOf(false) }
    var showBackConfirmationDialog by remember { mutableStateOf(false) }

    val hintsCount by viewModel.hintsCount.collectAsState()

    var showDeductionAnimation by remember { mutableStateOf(false) }
    var deductionOffset by remember { mutableStateOf(0f) }
    var deductionAlpha by remember { mutableStateOf(1f) }

    LaunchedEffect(showDeductionAnimation) {
        if (showDeductionAnimation) {
            deductionOffset = 0f
            deductionAlpha = 1f
            val duration = 1200
            val step = 16
            var currentMs = 0
            while (currentMs < duration) {
                kotlinx.coroutines.delay(step.toLong())
                currentMs += step
                val fraction = currentMs.toFloat() / duration
                deductionOffset = -120f * fraction
                deductionAlpha = 1f - fraction
            }
            showDeductionAnimation = false
        }
    }

    // Reset local option selection variables whenever question index advances
    LaunchedEffect(currentIndex) {
        selectedOptionIndex = null
        isSubmitted = false
        showSpellDialog = false
    }

    // Intercept hardware and gesture-based physical system back clicks
    BackHandler(enabled = true) {
        SoundManager.playClick()
        viewModel.stopTimer()
        showBackConfirmationDialog = true
    }

    // Screen-level localizations
    val scoreHeader = if (isSinhala) "පියවර" else "QUESTION"
    val hintButtonLabel = if (isSinhala) "විජ්ජා මන්ත්‍ර ලියවිල්ල" else "WIZARD'S SCROLL HINT"
    val spellFiftyFiftyLabel = if (isSinhala) "50-50 මන්ත්‍රය" else "Cast 50-50 Spell"
    val loadingQuestionsText = if (isSinhala) "ප්‍රශ්න සකස් කරමින් පවතී, කරුණාකර රැඳී සිටින්න..." else "Generating questions, please wait..."
    val submitBtnText = if (isSinhala) "තහවුරු කරන්න" else "SUBMIT ANSWER"
    val nextQuestionBtnText = if (isSinhala) "ඊළඟ ප්‍රශ්නය" else "NEXT QUESTION"
    val claimResultsBtnText = if (isSinhala) "ප්‍රතිඵල ලබා ගන්න" else "CLAIM GLORY & RESULTS"
    
    val isLastQuestion = currentIndex == questions.size - 1
    val actionBtnText = if (isLastQuestion) claimResultsBtnText else nextQuestionBtnText
    
    // Back dialog localizations
    val dialogTitle = if (isSinhala) "පලා යාමට සූදානම්ද?" else "RETREAT CONFIRMATION"
    val dialogMessage = if (isSinhala) "ඔබ මෙම සටනින් පසුබැසීමට කැමතිද? මෙතෙක් ලැබූ ලකුණු සියල්ල අහිමි වනු ඇත." else "Are you sure you want to retreat? Your current quest progress will be lost."
    val dialogRetreat = if (isSinhala) "පසුබසින්න (RETREAT)" else "RETREAT"
    val dialogContinue = if (isSinhala) "නැවත සටනට" else "CONTINUE QUEST"

    val currentQuestion = if (questions.isNotEmpty() && currentIndex < questions.size) {
        questions[currentIndex]
    } else null

    val isAnswerActionLocked = selectionFeedback != null || isSubmitted

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quiz_screen_root")
    ) {
        // Celestial animated stars
        AnimatedStarField(theme = theme)

        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    color = theme.accent,
                    strokeWidth = 4.dp,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = loadingQuestionsText,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        } else if (error != null || questions.isEmpty()) {
            val errorText = error ?: if (isSinhala) "ප්‍රශ්න කිසිවක් සකස් වී නොමැත. නැවත උත්සාහ කරන්න." else "No questions loaded. Tap retry to try again"
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color(0xFFEF4444),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorText,
                    color = Color(0xFFFCA5A5),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp).testTag("error_message")
                )
                Spacer(modifier = Modifier.height(24.dp))
                NeonButton(
                    text = if (isSinhala) "නැවත උත්සාහ කරන්න" else "RETRY",
                    theme = theme,
                    onClick = {
                        SoundManager.playClick()
                        viewModel.loadQuestions()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .testTag("retry_button")
                )
            }
        } else if (currentQuestion != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ROW 1: System controls & Life line
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            SoundManager.playClick()
                            viewModel.stopTimer()
                            showBackConfirmationDialog = true
                        },
                        modifier = Modifier.testTag("back_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Leave battle",
                            tint = theme.accent
                        )
                    }

                    HeartsRepresentationBar(lives = lives)
                }

                // OFFLINE STATUS BAR
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0x33F59E0B))
                        .border(1.dp, Color(0x66F59E0B), RoundedCornerShape(8.dp))
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFF59E0B))
                        )
                        Text(
                            text = if (isSinhala) "නොබැඳි මාදිලිය: ප්‍රශ්න 10 ක් ඇත" else "Offline Quest: 10 questions ready",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Refresh Button (Triggers local shuffle and load)
                    Text(
                        text = if (isSinhala) "නැවත සකසන්න ↻" else "Reshuffle ↻",
                        color = theme.accent,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                SoundManager.playClick()
                                viewModel.loadQuestions(forceRefresh = true)
                            }
                            .padding(4.dp)
                            .testTag("refresh_questions_button")
                    )
                }

                // LINEAR TIMER BAR
                val progressRatio = timerSeconds / 30f
                val timerColor = when {
                    timerSeconds > 15 -> theme.primary
                    timerSeconds > 7 -> theme.portalColor
                    else -> Color(0xFFEF4444)
                }
                
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$scoreHeader ${currentIndex + 1} OF ${questions.size}",
                            color = theme.accent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "Timer icon",
                                tint = timerColor,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${timerSeconds}s",
                                color = timerColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    LinearProgressIndicator(
                        progress = { progressRatio },
                        color = timerColor,
                        trackColor = Color.White.copy(alpha = 0.1f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                    )
                }

                // MAIN QUESTION HOVER CARD
                GlassCard(
                    theme = theme,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .testTag("question_card")
                ) {
                    Text(
                        text = if (isSinhala) currentQuestion.questionSinhala else currentQuestion.question,
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        lineHeight = 25.sp,
                        modifier = Modifier.testTag("question_text")
                    )
                    
                    // Display difficulty badge nicely
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(theme.primary.copy(alpha = 0.2f))
                                .border(1.dp, theme.primary.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = currentQuestion.difficulty.uppercase(),
                                color = theme.accent,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        if (currentQuestion.grade > 0) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(theme.secondary.copy(alpha = 0.2f))
                                    .border(1.dp, theme.secondary.copy(alpha = 0.4f), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "GRADE ${currentQuestion.grade}",
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // OPTIONS LIST SELECTION LAYOUT
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val optionsToRender = if (isSinhala) currentQuestion.optionsSinhala else currentQuestion.options
                    optionsToRender.forEachIndexed { index, option ->
                        val isDisabled = disabledOptions.contains(index)
                        
                        if (!isDisabled) {
                            // Highlights layout based on tap-then-submit flow rules
                            val isSelectedBeforeSubmit = selectedOptionIndex == index
                            val isChosenSubmitted = selectionFeedback == index && isSubmitted
                            val correctIndex = currentQuestion.correctAnswerIndex
                            val isThisCorrect = index == correctIndex

                            // Glow coloring options
                            val borderAccent = when {
                                // 1. Post-submit feedback highlights
                                isChosenSubmitted && isCorrectFeedback == true -> Color(0xFF10B981) // emerald success
                                isChosenSubmitted && isCorrectFeedback == false -> Color(0xFFEF4444) // coral error
                                isAnswerActionLocked && isThisCorrect -> Color(0xFF10B981) // reveal correct answer in green
                                
                                // 2. Pre-submit selected choice: GLOWING BLUE
                                isSelectedBeforeSubmit -> Color(0xFF00BFFF) // glowing blue/deep sky blue
                                
                                // 3. Idle style
                                else -> theme.primary.copy(alpha = 0.3f)
                            }
                            
                            val cardBg = when {
                                isChosenSubmitted && isCorrectFeedback == true -> Color(0x3310B981)
                                isChosenSubmitted && isCorrectFeedback == false -> Color(0x33EF4444)
                                isAnswerActionLocked && isThisCorrect -> Color(0x3310B981)
                                isSelectedBeforeSubmit -> Color(0x3300BFFF) // glowing blue background highlight
                                else -> theme.surface.copy(alpha = 0.3f)
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(cardBg)
                                    .border(
                                        width = if (isSelectedBeforeSubmit || isChosenSubmitted || (isAnswerActionLocked && isThisCorrect)) 2.dp else 1.dp,
                                        color = borderAccent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable(enabled = !isAnswerActionLocked) {
                                        SoundManager.playClick()
                                        selectedOptionIndex = index
                                    }
                                    .padding(horizontal = 20.dp, vertical = 14.dp)
                                    .testTag("option_${index}"),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = option,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = if (isSelectedBeforeSubmit) FontWeight.Bold else FontWeight.Medium,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                        }
                    }
                }

                // CONFIRM SUBMIT / NEXT ACTION BUTTON PANEL
                if (isAnswerActionLocked) {
                    NeonButton(
                        text = actionBtnText,
                        theme = theme,
                        onClick = {
                            viewModel.goToNextQuestion(onQuizFinished)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("next_question_button")
                    )
                } else {
                    AnimatedVisibility(
                        visible = selectedOptionIndex != null,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        NeonButton(
                            text = submitBtnText,
                            theme = theme,
                            onClick = {
                                if (selectedOptionIndex != null && !isSubmitted) {
                                    isSubmitted = true
                                    viewModel.submitAnswer(selectedOptionIndex!!, onQuizFinished)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .testTag("submit_button")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // LOWER DECK: RPG Helper spells (50-50, scroll hint)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 50/50 spell block
                        Button(
                            onClick = {
                                SoundManager.playClick()
                                viewModel.useFiftyFifty()
                            },
                            enabled = !isFiftyFiftyUsed && !isAnswerActionLocked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = theme.secondary.copy(alpha = 0.2f),
                                disabledContainerColor = Color.White.copy(alpha = 0.05f)
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .border(
                                    1.dp,
                                    if (isFiftyFiftyUsed) Color.Transparent else theme.accent.copy(alpha = 0.4f),
                                    RoundedCornerShape(26.dp)
                                ),
                            shape = RoundedCornerShape(26.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = "Spell casting 50/50 icon",
                                tint = if (isFiftyFiftyUsed) Color.Gray else theme.accent
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isFiftyFiftyUsed) "FIFTY USED" else spellFiftyFiftyLabel,
                                color = if (isFiftyFiftyUsed) Color.Gray else Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Expansion scroll hint trigger button
                        val hintButtonText = if (isSinhala) {
                            when (hintsCount) {
                                3 -> "💡 ඉඟිය (ඉතිරි 3)"
                                2 -> "💡 ඉඟිය (ඉතිරි 2)"
                                1 -> "💡 ඉඟිය (ඉතිරි 1)"
                                else -> "ඉඟි නැත"
                            }
                        } else {
                            when (hintsCount) {
                                3 -> "💡 Hint (3 left)"
                                2 -> "💡 Hint (2 left)"
                                1 -> "💡 Hint (1 left)"
                                else -> "No hints remaining"
                            }
                        }

                        val isHintEnabled = hintsCount > 0 || showSpellDialog

                        Button(
                            onClick = {
                                SoundManager.playClick()
                                if (showSpellDialog) {
                                    showSpellDialog = false
                                } else {
                                    if (hintsCount > 0) {
                                        val success = viewModel.useHint()
                                        if (success) {
                                            showDeductionAnimation = true
                                            showSpellDialog = true
                                        }
                                    }
                                }
                            },
                            enabled = isHintEnabled && !isAnswerActionLocked,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = theme.primary.copy(alpha = 0.2f),
                                disabledContainerColor = Color.White.copy(alpha = 0.05f)
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(52.dp)
                                .border(
                                    1.dp,
                                    if (!isHintEnabled) Color.Transparent else theme.accent.copy(alpha = 0.4f),
                                    RoundedCornerShape(26.dp)
                                )
                                .testTag("hint_trigger_button"),
                            shape = RoundedCornerShape(26.dp)
                        ) {
                            Icon(
                                imageVector = if (hintsCount > 0) Icons.Default.Info else Icons.Default.Lock,
                                contentDescription = "Scroll Hint info button",
                                tint = if (!isHintEnabled) Color.Gray else theme.accent,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (hintsCount == 0 && !showSpellDialog) {
                                    if (isSinhala) "ඉඟි නැත" else "No hints remaining"
                                } else {
                                    hintButtonText
                                },
                                color = if (!isHintEnabled) Color.Gray else Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (showDeductionAnimation) {
                        Text(
                            text = if (isSinhala) "-5 ලකුණු" else "-5 points",
                            color = Color(0xFFEF4444),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 40.dp)
                                .offset(y = deductionOffset.dp)
                                .graphicsLayer(alpha = deductionAlpha)
                        )
                    }
                }

                // Smooth unfolding scroll hint scroll overlay
                AnimatedVisibility(
                    visible = showSpellDialog,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.Black.copy(alpha = 0.8f))
                            .border(1.dp, theme.portalColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = hintButtonLabel,
                                    color = theme.portalColor,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                IconButton(
                                    onClick = {
                                        SoundManager.playClick()
                                        showSpellDialog = false
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close hint button",
                                        tint = Color.White.copy(alpha = 0.6f),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            val displayHint = if (currentQuestion != null) {
                                if (isSinhala) currentQuestion.hintSinhala else currentQuestion.hint
                            } else ""
                            Text(
                                text = displayHint,
                                color = Color.White.copy(alpha = 0.95f),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }
            }
        }

        // CONFIRM RETREAT BACK DIALOG
        if (showBackConfirmationDialog) {
            AlertDialog(
                onDismissRequest = {
                    showBackConfirmationDialog = false
                    // Resume the timer seconds
                    viewModel.startQuiz() // Let's re-align core state
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Warning",
                        tint = Color(0xFFEF4444)
                    )
                },
                title = {
                    Text(
                        text = dialogTitle,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                },
                text = {
                    Text(
                        text = dialogMessage,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 14.sp
                    )
                },
                containerColor = Color(0xFF1E1B4B), // Custom cosmic dark blue backdrop
                confirmButton = {
                    TextButton(
                        onClick = {
                            showBackConfirmationDialog = false
                            onBack()
                        }
                    ) {
                        Text(
                            text = dialogRetreat,
                            color = Color(0xFFEF4444),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showBackConfirmationDialog = false
                            // Simple workaround: restart quiz timer or continue
                            viewModel.startQuiz()
                        }
                    ) {
                        Text(
                            text = dialogContinue,
                            color = theme.accent,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }
    }
}
