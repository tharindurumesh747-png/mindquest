package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import com.example.ui.components.FantasyCard
import com.example.ui.theme.LocalFantasyTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    viewModel: GameViewModel,
    onNavigateResults: () -> Unit,
    onNavigateBackToHome: () -> Unit
) {
    val theme = LocalFantasyTheme
    
    // Core game state observing
    val language by viewModel.language.collectAsState()
    val isSinhala = language == "Sinhala"
    
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()
    val currentQuestion = questions.getOrNull(currentIndex)
    
    val score by viewModel.score.collectAsState()
    val lives by viewModel.lives.collectAsState()
    val currentStreak by viewModel.currentStreak.collectAsState()
    val ticksRemaining by viewModel.ticksRemaining.collectAsState()
    val hintsCount by viewModel.hintsCount.collectAsState()
    val isFiftyFiftyUsed by viewModel.isFiftyFiftyUsed.collectAsState()
    val disabledOptions by viewModel.disabledOptions.collectAsState()
    
    val floatingScoreAnimation by viewModel.floatingScoreAnimation.collectAsState()
    val comboTextAnimation by viewModel.comboTextAnimation.collectAsState()
    val encouragingMessage by viewModel.encouragingMessage.collectAsState()
    val selectedAnswerIndex by viewModel.selectedAnswerIndex.collectAsState()
    val isCorrectFeedback by viewModel.isCorrectFeedback.collectAsState()
    val quizFinished by viewModel.quizFinished.collectAsState()

    // Local dialog display trigger
    var showBackConfirmation by remember { mutableStateOf(false) }
    var showSpellHintDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    // Intercept hardware and gesture-based physical back clicks
    BackHandler {
        showBackConfirmation = true
    }

    // Trigger navigation immediately to results screen when finished
    LaunchedEffect(quizFinished) {
        if (quizFinished) {
            onNavigateResults()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isSinhala) "දැනුම් සටන" else "BATTLE OF TRIVIA",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            SoundManager.playNavigation()
                            showBackConfirmation = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Leave battle run",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = theme.background
                )
            )
        },
        containerColor = theme.background
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(theme.background)
        ) {
            if (currentQuestion != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // ═══════════════════════════════════════
                    // STATS BOARD BAR
                    // ═══════════════════════════════════════
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // XP Score Indicator
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = theme.accent,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "XP: $score",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        // Hot Streak Display
                        if (currentStreak > 0) {
                            Text(
                                text = "🔥 Streak: $currentStreak",
                                color = theme.accent,
                                fontWeight = FontWeight.Black,
                                fontSize = 14.sp,
                                modifier = Modifier.testTag("hot_streak_badge")
                            )
                        }

                        // Lives Hearts indicator
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.testTag("lives_indicator_parent")
                        ) {
                            for (i in 1..3) {
                                val heartTint = if (lives >= i) theme.failColor else Color.Gray.copy(alpha = 0.5f)
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Health heart unit",
                                    tint = heartTint,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    // ═══════════════════════════════════════
                    // TIMER PRESSURE BAR (BUG 6.3)
                    // ═══════════════════════════════════════
                    val timerFraction = ticksRemaining.toFloat() / 30f
                    val timerColor = when {
                        timerFraction > 0.5f -> theme.successColor
                        timerFraction > 0.16f -> theme.accent
                        else -> theme.failColor
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        LinearProgressIndicator(
                            progress = timerFraction,
                            color = timerColor,
                            trackColor = Color.White.copy(alpha = 0.1f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .testTag("countdown_timer_bar")
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isSinhala) "කාලය" else "TIME RUNS",
                                color = Color.Gray,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$ticksRemaining s",
                                color = timerColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // ═══════════════════════════════════════
                    // QUESTION CARD
                    // ═══════════════════════════════════════
                    FantasyCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val displayQStr = if (isSinhala) currentQuestion.questionSinhala else currentQuestion.question
                        Text(
                            text = if (isSinhala) "ප්‍රශ්නය ${currentIndex + 1} / ${questions.size}" else "QUESTION ${currentIndex + 1} OF ${questions.size}",
                            color = theme.portalColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 2.sp,
                            modifier = Modifier.testTag("question_index_indicator")
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = displayQStr,
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp,
                            modifier = Modifier.testTag("question_text_view")
                        )
                    }

                    // ═══════════════════════════════════════
                    // SCROLL OPTIONS LIST
                    // ═══════════════════════════════════════
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val activeOptions = if (isSinhala) currentQuestion.optionsSinhala else currentQuestion.options
                        activeOptions.forEachIndexed { optIndex, optVal ->
                            val isChosen = selectedAnswerIndex == optIndex
                            val isCorrectAnswer = optIndex == currentQuestion.correctAnswerIndex
                            val isActionDisabled = disabledOptions.contains(optIndex)
                            val isLocked = selectedAnswerIndex != null

                            val optionBorderColor = when {
                                isActionDisabled -> Color.Transparent
                                isLocked && isCorrectAnswer -> theme.successColor
                                isLocked && isChosen && !isCorrectAnswer -> theme.failColor
                                else -> theme.primary.copy(alpha = 0.3f)
                            }

                            val optionBg = when {
                                isActionDisabled -> Color.White.copy(alpha = 0.02f)
                                isLocked && isCorrectAnswer -> theme.successColor.copy(alpha = 0.2f)
                                isLocked && isChosen && !isCorrectAnswer -> theme.failColor.copy(alpha = 0.2f)
                                else -> theme.surface.copy(alpha = 0.7f)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(optionBg)
                                    .border(1.dp, optionBorderColor, RoundedCornerShape(14.dp))
                                    .clickable(enabled = !isLocked && !isActionDisabled) {
                                        viewModel.submitAnswer(optIndex)
                                    }
                                    .padding(horizontal = 16.dp)
                                    .testTag("option_$optIndex"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isActionDisabled) "---" else optVal,
                                    color = if (isActionDisabled) Color.Gray else Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily.SansSerif
                                )

                                // Trailing status icons
                                if (isLocked) {
                                    if (isCorrectAnswer) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Correct marker",
                                            tint = theme.successColor
                                        )
                                    } else if (isChosen) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Wrong marker",
                                            tint = theme.failColor
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // ═══════════════════════════════════════
                    // ENCOURAGING MESSAGES BLOCK (BUG 6.5)
                    // ═══════════════════════════════════════
                    AnimatedVisibility(
                        visible = encouragingMessage != null,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        encouragingMessage?.let { msg ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(
                                        if (isCorrectFeedback == true) theme.successColor.copy(alpha = 0.1f) 
                                        else theme.failColor.copy(alpha = 0.1f)
                                    )
                                    .border(
                                        1.dp,
                                        if (isCorrectFeedback == true) theme.successColor.copy(alpha = 0.4f) 
                                        else theme.failColor.copy(alpha = 0.4f),
                                        RoundedCornerShape(12.dp)
                                    )
                                    .padding(12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = msg,
                                    color = if (isCorrectFeedback == true) theme.successColor else theme.failColor,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.testTag("encouragement_card_text")
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // ═══════════════════════════════════════
                    // LOWER DECK: RPG HELPER SPELLS (BUG 4 & 6)
                    // ═══════════════════════════════════════
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 1. 50-50 Spell button
                        Button(
                            onClick = {
                                viewModel.useFiftyFifty()
                            },
                            enabled = !isFiftyFiftyUsed && selectedAnswerIndex == null,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = theme.secondary.copy(alpha = 0.15f),
                                disabledContainerColor = Color.White.copy(alpha = 0.05f)
                            ),
                            modifier = Modifier
                                .weight(1.0f)
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    if (isFiftyFiftyUsed) Color.Transparent else theme.portalColor,
                                    RoundedCornerShape(25.dp)
                                )
                                .testTag("spell_fifty_fifty_button"),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Cast fifty fifty spell",
                                tint = if (isFiftyFiftyUsed) Color.Gray else theme.accent,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (isSinhala) "50/50 විජ්ජාව" else "50/50 SPELL",
                                color = if (isFiftyFiftyUsed) Color.Gray else Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 2. Hint Spell Scroll button
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
                                else -> "No hints left"
                            }
                        }

                        Button(
                            onClick = {
                                if (showSpellHintDialog) {
                                    showSpellHintDialog = false
                                } else {
                                    if (hintsCount > 0) {
                                        val success = viewModel.useHint()
                                        if (success) {
                                            showSpellHintDialog = true
                                        }
                                    }
                                }
                            },
                            enabled = (hintsCount > 0 || showSpellHintDialog) && selectedAnswerIndex == null,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = theme.primary.copy(alpha = 0.15f),
                                disabledContainerColor = Color.White.copy(alpha = 0.05f)
                            ),
                            modifier = Modifier
                                .weight(1.0f)
                                .height(50.dp)
                                .border(
                                    1.dp,
                                    if (hintsCount == 0 && !showSpellHintDialog) Color.Transparent else theme.accent,
                                    RoundedCornerShape(25.dp)
                                )
                                .testTag("hint_trigger_button"),
                            shape = RoundedCornerShape(25.dp)
                        ) {
                            Icon(
                                imageVector = if (hintsCount > 0) Icons.Default.Info else Icons.Default.Lock,
                                contentDescription = "Reveal hint tool",
                                tint = if (hintsCount > 0) theme.accent else Color.Gray,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = hintButtonText,
                                color = if (hintsCount == 0 && !showSpellHintDialog) Color.Gray else Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Interactive Spell Hint panel sheet
                    AnimatedVisibility(
                        visible = showSpellHintDialog && selectedAnswerIndex == null,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black.copy(alpha = 0.8f))
                                .border(1.dp, theme.portalColor.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isSinhala) "📜 ඉඟිය හෙළිදරව් කෙරේ" else "📜 COGNITIVE HINT REVEALED",
                                    color = theme.accent,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 2.sp
                                )
                                IconButton(
                                    onClick = {
                                        SoundManager.playButtonTap()
                                        showSpellHintDialog = false
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close hint screen",
                                        tint = Color.White.copy(alpha = 0.6f),
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            val displayHintStr = if (isSinhala) currentQuestion.hintSinhala else currentQuestion.hint
                            Text(
                                text = displayHintStr,
                                color = Color.White.copy(alpha = 0.95f),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }
            }

            // ═══════════════════════════════════════
            // FLOATING SCORE ANIMATION & COMBO TRIGGER (BUG 6.1 & 6.2)
            // ═══════════════════════════════════════
            
            // 1. Floating Score Overlay
            floatingScoreAnimation?.let { flNote ->
                val isDeduction = flNote.startsWith("-")
                val textColor = if (isDeduction) theme.failColor else theme.gold
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    var animActive by remember { mutableStateOf(false) }
                    LaunchedEffect(flNote) {
                        animActive = true
                    }
                    val offsetVal by animateDpAsState(
                        targetValue = if (animActive) (-140).dp else 0.dp,
                        animationSpec = tween(1200, easing = LinearOutSlowInEasing)
                    )
                    val alphaVal by animateFloatAsState(
                        targetValue = if (animActive) 0f else 1f,
                        animationSpec = tween(1200, easing = LinearEasing)
                    )

                    Text(
                        text = flNote,
                        color = textColor,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .offset(y = offsetVal)
                            .graphicsLayer(alpha = alphaVal)
                            .testTag("floating_score_text")
                    )
                }
            }

            // 2. Bursting Combo Badge Overlay
            comboTextAnimation?.let { comboStr ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    var scaleActive by remember { mutableStateOf(false) }
                    LaunchedEffect(comboStr) {
                        scaleActive = true
                    }
                    val scaleFactor by animateFloatAsState(
                        targetValue = if (scaleActive) 2.2f else 0.5f,
                        animationSpec = tween(800, easing = FastOutSlowInEasing)
                    )
                    val alphaFactor by animateFloatAsState(
                        targetValue = if (scaleActive) 0f else 1f,
                        animationSpec = tween(800)
                    )

                    Text(
                        text = comboStr,
                        color = theme.accent,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .scale(scaleFactor)
                            .graphicsLayer(alpha = alphaFactor)
                            .testTag("floating_combo_badge")
                    )
                }
            }

            // ═══════════════════════════════════════
            // BACK CONFIRMATION DIALOG (BUG 1)
            // ═══════════════════════════════════════
            if (showBackConfirmation) {
                AlertDialog(
                    onDismissRequest = { showBackConfirmation = false },
                    title = {
                        Text(
                            text = if (isSinhala) "මෙහෙයුම අතහැර දැමීම?" else "ABANDON QUEST?",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    text = {
                        Text(
                            text = if (isSinhala) 
                                "ඔබ මෙම සටනින් ඉවත් වුවහොත්, රැස්කරගත් සියලුම ලකුණු අහිමි වනු ඇත! ඉවත් වීමට අවශ්‍යද?"
                                else "Are you sure you want to retreat? Your current quest points will be surrendered!",
                            color = Color.LightGray
                        )
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                SoundManager.playButtonTap()
                                showBackConfirmation = false
                                viewModel.resetQuizState()
                                onNavigateBackToHome()
                            },
                            modifier = Modifier.testTag("confirm_retreat_button")
                        ) {
                            Text(
                                text = if (isSinhala) "ඔව්, පිටවෙන්න" else "YES, RETREAT",
                                color = theme.failColor,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                SoundManager.playButtonTap()
                                showBackConfirmation = false
                            }
                        ) {
                            Text(
                                text = if (isSinhala) "නැත, සටන කරමු" else "NO, FIGHT ON",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    containerColor = theme.surface,
                    titleContentColor = Color.White,
                    textContentColor = Color.LightGray
                )
            }
        }
    }
}
