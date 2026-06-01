package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.StateManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.GlassCard
import com.example.ui.components.NeonButton
import com.example.ui.theme.FantasyTheme

@Composable
fun ResultScreen(
    score: Int,
    earnedXp: Int,
    grade: Int,
    subject: String,
    level: Int,
    theme: FantasyTheme,
    stateManager: StateManager,
    onRetry: () -> Unit,
    onNextLevel: (() -> Unit)?,
    onReturnToMap: () -> Unit
) {
    val currentLang by stateManager.language.collectAsState()
    val isSinhala = currentLang == "si"

    // RPG Titles mapping to player performance
    val (starCount, perfTitle, perfDesc) = when (score) {
        10 -> Triple(
            3,
            if (isSinhala) "මහා ප්‍රඥාවන්තයා!" else "LEGENDARY ARITHMANCER!",
            if (isSinhala) "ඔබ ප්‍රශ්න සියල්ලටම නිවැරදිව පිළිතුරු සපයමින් මනාව ජය ලබා ඇත!" else "You achieved perfect mastery in this educational quest!"
        )
        in 7..9 -> Triple(
            2,
            if (isSinhala) "ධෛර්යවන්ත රණශූරයා!" else "VALIANT KNIGHT!",
            if (isSinhala) "ඔබ විශිෂ්ට ජයක් ලබා ගෙන ඇත! තව එක අඩියක් ඉදිරියට තබන්න." else "Excellent work! Keep defending the towers of knowledge."
        )
        in 4..6 -> Triple(
            1,
            if (isSinhala) "ආරම්භක වීරයා!" else "NOBLE SQUIRE!",
            if (isSinhala) "ජයග්‍රහණය පෙනෙන මානයේ ඇත. නැවත උත්සාහ කරන්න!" else "A solid effort. Practice more to refine your magic spells."
        )
        else -> Triple(
            0,
            if (isSinhala) "පුහුණුවීම් අවශ්‍යයි!" else "QUEST FAILED",
            if (isSinhala) "තවදුරටත් මන්ත්‍ර පොත් ප්‍රගුණ කිරීම අවශ්‍ය වේ. අධෛර්යමත් නොවන්න!" else "Do not surrender, young apprentice! Study the scrolls and try again."
        )
    }

    // Localized Strings
    val scoreBadgeLabel = if (isSinhala) "ලකුණු ප්‍රමාණය" else "SCORE"
    val xpRewardBadgeLabel = if (isSinhala) "ලැබූ XP" else "REWARDED XP"
    val retryBtnText = if (isSinhala) "නැවත සටන් කරන්න" else "RETRY QUEST"
    val nextLevelBtnText = if (isSinhala) "ඊළඟ පියවරට" else "NEXT DUNGEON DEPTH"
    val returnBtnText = if (isSinhala) "සිතියමට යන්න" else "RETURN TO MAP"

    // Infinite scaling animation for stars pulse
    val infiniteTransition = rememberInfiniteTransition(label = "stars_pulse")
    val starScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "sc"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("result_screen_root")
    ) {
        AnimatedStarField(theme = theme)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // GOLDEN POOL STARS RENDERER
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .scale(starScale)
                    .testTag("star_row")
            ) {
                repeat(3) { index ->
                    val isAwarded = index < starCount
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star $index",
                        tint = if (isAwarded) Color(0xFFFBBF24) else Color.White.copy(alpha = 0.15f),
                        modifier = Modifier.size(if (index == 1) 56.dp else 40.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // TITLE AND STORY DESCRIPTIONS
            Text(
                text = perfTitle,
                color = theme.accent,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.testTag("result_title")
            )

            Text(
                text = perfDesc,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // STATS BREAKDOWN GRID (Glassmorphism Cards)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Score Box
                GlassCard(
                    theme = theme,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("score_card")
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = scoreBadgeLabel,
                            color = theme.primary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$score / 10",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                // XP Earned Box
                GlassCard(
                    theme = theme,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("xp_card")
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = xpRewardBadgeLabel,
                            color = theme.portalColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "+$earnedXp",
                            color = Color(0xFF10B981),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // CTA ACTIONS BUTTON COLUMN
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Next level / depth button (if available)
                if (onNextLevel != null) {
                    NeonButton(
                        text = "$nextLevelBtnText  ⚡",
                        theme = theme,
                        onClick = onNextLevel,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .testTag("next_level_button")
                    )
                }

                // Retry Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(theme.surface.copy(alpha = 0.3f))
                        .border(1.dp, theme.primary.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                        .clickable(onClick = onRetry),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Retry icon",
                            tint = Color.White
                        )
                        Text(
                            text = retryBtnText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }

                // Return to map button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.Transparent)
                        .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                        .clickable(onClick = onReturnToMap),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = "Map icon",
                            tint = theme.accent
                        )
                        Text(
                            text = returnBtnText,
                            color = theme.accent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}
