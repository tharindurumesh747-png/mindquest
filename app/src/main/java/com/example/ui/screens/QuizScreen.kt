package com.example.ui.screens

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.GameViewModel
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

    // Collect flow states
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

    var showSpellDialog by remember { mutableStateOf(false) }

    // Screen-level localizations
    val scoreHeader = if (isSinhala) "පියවර" else "QUESTION"
    val hintButtonLabel = if (isSinhala) "විජ්ජා මන්ත්‍ර ලියවිල්ල" else "WIZARD'S SCROLL HINT"
    val spellFiftyFiftyLabel = if (isSinhala) "50-50 මන්ත්‍රය" else "Cast 50-50 Spell"
    val loadingQuestionsText = if (isSinhala) "මන්ත්‍ර ද්වාරයෙන් ප්‍රශ්න කැඳවමින්..." else "Summoning questions from the scrolls..."

    val currentQuestion = if (questions.isNotEmpty() && currentIndex < questions.size) {
        questions[currentIndex]
    } else null

    // Block answer submissions if click feedback is already active to prevent multi-taps
    val isAnswerActionLocked = selectionFeedback != null

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quiz_screen_root")
    ) {
        AnimatedStarField(theme = theme)

        if (isLoading) {
            // Loading Animation and wizard text
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
                            viewModel.stopTimer()
                            onBack()
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
                        text = currentQuestion.text,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif,
                        lineHeight = 26.sp,
                        modifier = Modifier.testTag("question_text")
                    )
                }

                // OPTIONS SELECTIONS
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    currentQuestion.options.forEachIndexed { index, option ->
                        val isDisabled = disabledOptions.contains(index)
                        
                        // Handle opacity if hidden by 50/50 hint
                        if (!isDisabled) {
                            val isChosen = selectionFeedback == index
                            val correctIndex = currentQuestion.correctAnswerIndex
                            val isThisCorrect = index == correctIndex

                            // Determine highlight state
                            val borderAccent = when {
                                isChosen && isCorrectFeedback == true -> Color(0xFF10B981) // emerald success
                                isChosen && isCorrectFeedback == false -> Color(0xFFEF4444) // coral error
                                isAnswerActionLocked && isThisCorrect -> Color(0xFF10B981) // highlight correct answer
                                else -> theme.primary.copy(alpha = 0.3f)
                            }
                            
                            val cardBg = when {
                                isChosen && isCorrectFeedback == true -> Color(0x3310B981)
                                isChosen && isCorrectFeedback == false -> Color(0x33EF4444)
                                isAnswerActionLocked && isThisCorrect -> Color(0x3310B981)
                                else -> theme.surface.copy(alpha = 0.3f)
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(cardBg)
                                    .border(
                                        width = if (isChosen || (isAnswerActionLocked && isThisCorrect)) 2.dp else 1.dp,
                                        color = borderAccent,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .clickable(enabled = !isAnswerActionLocked) {
                                        viewModel.submitAnswer(index, onQuizFinished)
                                    }
                                    .padding(horizontal = 20.dp, vertical = 14.dp)
                                    .testTag("option_${index}"),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = option,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // LOWER DECK: RPG Helper spells (50-50, Expansion scroll hint, etc.)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 50/50 magical casting block
                    Button(
                        onClick = { viewModel.useFiftyFifty() },
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
                    IconButton(
                        onClick = { showSpellDialog = !showSpellDialog },
                        modifier = Modifier
                            .size(52.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .background(theme.primary.copy(alpha = 0.15f))
                            .border(1.dp, theme.primary.copy(alpha = 0.4f), RoundedCornerShape(26.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Scroll Hint info button",
                            tint = theme.accent
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
                            .background(Color.Black.copy(alpha = 0.6f))
                            .border(1.dp, theme.portalColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .padding(14.dp)
                    ) {
                        Column {
                            Text(
                                text = hintButtonLabel,
                                color = theme.portalColor,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 2.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = currentQuestion.hint,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }
            }
        } else {
            // Null state catch fallback
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No questions loaded.\nTry reloading the quest.",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
