package com.example.ui.screens

import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.GameViewModel
import com.example.SoundManager
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyCard
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme
import kotlinx.coroutines.delay

@Composable
fun ResultsScreen(
    viewModel: GameViewModel,
    onNavigatePlayAgain: () -> Unit,
    onNavigateBackToHome: () -> Unit
) {
    val theme = LocalFantasyTheme
    val context = LocalContext.current
    
    val language by viewModel.language.collectAsState()
    val isSinhala = language == "Sinhala"
    
    val finalScore by viewModel.score.collectAsState()
    val livesRemaining by viewModel.lives.collectAsState()
    val correctAnswersCount by viewModel.correctAnswersCount.collectAsState()
    val totalQuestions by viewModel.questions.collectAsState()
    val bestStreak by viewModel.bestStreak.collectAsState()
    val timeSpentSec by viewModel.totalTimeSpentSec.collectAsState()
    val currentStage by viewModel.currentStage.collectAsState()

    val totalQCount = totalQuestions.size.coerceAtLeast(1)
    val accuracy = ((correctAnswersCount.toFloat() / totalQCount.toFloat()) * 100).toInt()

    // Calculate stars awarded (0, 1, 2, or 3)
    val starsAwarded = when {
        livesRemaining == 0 -> 0
        accuracy == 100 -> 3
        accuracy >= 65 -> 2
        accuracy >= 30 -> 1
        else -> 0
    }

    // RPG Win / Loss labels
    val resultTitle = if (livesRemaining > 0) {
        if (isSinhala) "මෙහෙයුම සාර්ථකයි! 🏆" else "QUEST COMPLETED! 🏆"
    } else {
        if (isSinhala) "නැවත උත්සාහ කරන්න! 💀" else "CHALLENGE FAILED! 💀"
    }
    
    val resultSubtitle = if (livesRemaining > 0) {
        if (isSinhala) "ඔබේ සටන සාර්ථකව අවසන්!" else "SCROLL OF TRUTH UNLOCKED!"
    } else {
        if (isSinhala) "ජීවිත සියල්ල අහිමි විය" else "YOUR HEARTS WERE DEFLETED"
    }

    // Animation progress states
    var animatedScore by remember { mutableStateOf(0) }
    var showStarsTrigger by remember { mutableStateOf(0) }

    val scrollState = rememberScrollState()

    LaunchedEffect(finalScore) {
        // Count-up final score animation
        val steps = 20
        val delayTime = 30L
        for (i in 1..steps) {
            delay(delayTime)
            animatedScore = (finalScore * (i.toFloat() / steps.toFloat())).toInt()
        }
        animatedScore = finalScore
    }

    // Progressive key-frame delay for stars animation
    LaunchedEffect(starsAwarded) {
        delay(600)
        for (j in 1..starsAwarded) {
            delay(400)
            showStarsTrigger = j
            SoundManager.playLevelUp() // magical sparkle sound per star popped!
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(theme.background, Color(0xFF0F0822))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Main battle status
            FantasyTitle(text = resultTitle, subtitle = resultSubtitle)

            Spacer(modifier = Modifier.height(4.dp))

            // ═══════════════════════════════════════
            // ANIMATED STARS SECTION (BUG 6.6)
            // ═══════════════════════════════════════
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .testTag("stars_summary_container")
            ) {
                for (s in 1..3) {
                    val isAwarded = s <= starsAwarded
                    val isShown = s <= showStarsTrigger
                    
                    val starScale by animateFloatAsState(
                        targetValue = if (isShown) 1.3f else 0.8f,
                        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
                    )

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Victory star status index $s",
                        tint = if (isAwarded && isShown) theme.accent else Color.White.copy(alpha = 0.15f),
                        modifier = Modifier
                            .size(54.dp)
                            .scale(starScale)
                    )
                }
            }

            // Stats Breakdown Card
            FantasyCard(modifier = Modifier.fillMaxWidth()) {
                // Animated count-up Score
                Text(
                    text = if (isSinhala) "ලබාගත් මුළු ලකුණු (XP)" else "TOTAL XP EARNED",
                    color = theme.portalColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "$animatedScore XP",
                    color = theme.accent,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.testTag("final_score_value")
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(16.dp))

                // Detail Items Row Builders
                val buildDetailRow = @Composable { icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, valStr: String, dTag: String ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = theme.portalColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = label,
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Text(
                            text = valStr,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.testTag(dTag)
                        )
                    }
                }

                // Row stats
                buildDetailRow(
                    Icons.Default.Check,
                    if (isSinhala) "නිවැරදි පිළිතුරු" else "Correct Answers",
                    "$correctAnswersCount / $totalQCount",
                    "correct_answers_stat"
                )

                buildDetailRow(
                    Icons.Default.Star,
                    if (isSinhala) "නිවැරදි ප්‍රතිශතය" else "Accuracy",
                    "$accuracy%",
                    "accuracy_stat"
                )

                buildDetailRow(
                    Icons.Default.Star,
                    if (isSinhala) "උපරිම ප්‍රවාහය (Streak)" else "Best Streak",
                    "🔥 $bestStreak",
                    "best_streak_stat"
                )

                val mins = timeSpentSec / 60
                val secs = timeSpentSec % 60
                val durationFormatted = String.format("%02d:%02d", mins, secs)

                buildDetailRow(
                    Icons.Default.PlayArrow,
                    if (isSinhala) "ගතවූ කාලය" else "Time Spent",
                    durationFormatted,
                    "time_spent_stat"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Play Control buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // PLAY AGAIN button
                FantasyButton(
                    text = if (isSinhala) "නැවත සටනට ⚔️" else "PLAY AGAIN ⚔️",
                    onClick = {
                        viewModel.resetQuizState()
                        onNavigatePlayAgain()
                    },
                    accentColor = theme.accent,
                    testTag = "play_again_on_results"
                )

                // NEXT STAGE Progression Button
                if (currentStage < 3 && livesRemaining > 0) {
                    FantasyButton(
                        text = if (isSinhala) "ඊළඟ අදියරට යන්න ⏩" else "NEXT STAGE ⏩",
                        onClick = {
                            viewModel.setStage(currentStage + 1)
                            viewModel.resetQuizState()
                            onNavigatePlayAgain()
                        },
                        accentColor = theme.primary,
                        testTag = "next_stage_button"
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // RETURN TO HOME Button
                    Button(
                        onClick = {
                            SoundManager.playNavigation()
                            viewModel.resetQuizState()
                            onNavigateBackToHome()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = theme.surface.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(26.dp))
                            .testTag("results_home_button"),
                        shape = RoundedCornerShape(26.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Home return button")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = if (isSinhala) "මුල් පිටුව" else "HOME", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }

                    // SHARE SCORE Button (BUG 6.6)
                    Button(
                        onClick = {
                            SoundManager.playButtonTap()
                            val shareMessage = if (isSinhala) {
                                "මම මයින්ඩ් ක්වෙස්ට් ක්‍රීඩාවේ ${currentStage} වන අදියරෙන් ලකුණු $finalScore XP ක් ලබා ගත්තා! ඔබත් ක්‍රීඩා කරන්න!"
                            } else {
                                "I scored $finalScore XP on Stage $currentStage of MindQuest Educational battle! Try to beat my score!"
                            }
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, shareMessage)
                            }
                            context.startActivity(Intent.createChooser(intent, "Share MindQuest Score"))
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = theme.primary.copy(alpha = 0.2f)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .border(1.dp, theme.primary.copy(alpha = 0.4f), RoundedCornerShape(26.dp))
                            .testTag("results_share_button"),
                        shape = RoundedCornerShape(26.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share score link")
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = if (isSinhala) "බෙදාගන්න" else "SHARE", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
