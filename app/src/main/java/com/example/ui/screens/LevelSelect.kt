package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.StateManager
import com.example.data.PlaySessionManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.theme.FantasyTheme

@Composable
fun LevelSelectScreen(
    grade: Int,
    subject: String,
    theme: FantasyTheme,
    stateManager: StateManager,
    onLevelSelected: (Int) -> Unit,
    onBack: () -> Unit
) {
    val currentLang by stateManager.language.collectAsState()
    val isSinhala = currentLang == "si"

    val titleText = if (isSinhala) "අභියෝග මට්ටම" else "SELECT LEVEL"
    val subtitleText = if (isSinhala) "${subject} විෂයයි • $grade ශ්‍රේණියයි" else "$subject • Grade $grade"

    // Level localized labels
    val level1Title = if (isSinhala) "පියවර 01: රහස් ගුහාව" else "STAGE I: Whispering Caverns"
    val level1Desc = if (isSinhala) "ආරම්භක වීරයන් සඳහා" else "Gentle educational questions for beginners"

    val level2Title = if (isSinhala) "පියවර 02: සෙවනැලි වංකගිරිය" else "STAGE II: Shadow Labyrinth"
    val level2Desc = if (isSinhala) "ප්‍රවීණ වීරයන් සඳහා" else "An intermediate challenge for seasoned minds"

    val level3Title = if (isSinhala) "පියවර 03: තරු මාලිගාව" else "STAGE III: Citadel of Stars"
    val level3Desc = if (isSinhala) "මහා ප්‍රඥාවන්ත බුද්ධිමතුන් සඳහා" else "Legendary educational battle for the master rank"

    val context = LocalContext.current
    val star1 = PlaySessionManager.getStageStars(context, grade, subject, 1)
    val star2 = PlaySessionManager.getStageStars(context, grade, subject, 2)
    val star3 = PlaySessionManager.getStageStars(context, grade, subject, 3)

    val isUnlocked1 = PlaySessionManager.isStageUnlocked(context, grade, subject, 1)
    val isUnlocked2 = PlaySessionManager.isStageUnlocked(context, grade, subject, 2)
    val isUnlocked3 = PlaySessionManager.isStageUnlocked(context, grade, subject, 3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("level_select_root")
    ) {
        AnimatedStarField(theme = theme)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back",
                        tint = theme.accent
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = titleText,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.testTag("level_select_title")
                    )
                    Text(
                        text = subtitleText,
                        color = theme.primary,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Scrollable stages
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LevelStageCard(
                    level = 1,
                    title = level1Title,
                    description = level1Desc,
                    color = theme.primary,
                    theme = theme,
                    isUnlocked = isUnlocked1,
                    stars = star1,
                    onClick = { onLevelSelected(1) }
                )

                LevelStageCard(
                    level = 2,
                    title = level2Title,
                    description = level2Desc,
                    color = theme.secondary,
                    theme = theme,
                    isUnlocked = isUnlocked2,
                    stars = star2,
                    onClick = { onLevelSelected(2) }
                )

                LevelStageCard(
                    level = 3,
                    title = level3Title,
                    description = level3Desc,
                    color = theme.portalColor,
                    theme = theme,
                    isUnlocked = isUnlocked3,
                    stars = star3,
                    onClick = { onLevelSelected(3) }
                )
            }
        }
    }
}

@Composable
fun LevelStageCard(
    level: Int,
    title: String,
    description: String,
    color: Color,
    theme: FantasyTheme,
    isUnlocked: Boolean,
    stars: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (isUnlocked) theme.surface.copy(alpha = 0.4f) else theme.surface.copy(alpha = 0.15f))
            .border(
                width = 1.dp,
                color = if (isUnlocked) color.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.15f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(enabled = isUnlocked, onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Level Badge Block (containing either Lock icon or MilitaryTech badge)
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(if (isUnlocked) color.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.05f))
                    .border(2.dp, if (isUnlocked) color else Color.White.copy(alpha = 0.2f), RoundedCornerShape(26.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (isUnlocked) {
                    Icon(
                        imageVector = Icons.Default.MilitaryTech,
                        contentDescription = "Stage badge",
                        tint = color,
                        modifier = Modifier.size(28.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Stage locked",
                        tint = Color.White.copy(alpha = 0.4f),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Texts
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = if (isUnlocked) Color.White else Color.White.copy(alpha = 0.5f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = description,
                    color = if (isUnlocked) Color.White.copy(alpha = 0.6f) else Color.White.copy(alpha = 0.3f),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
                
                // Show stars if completed
                if (isUnlocked && stars >= 0) {
                    Row(
                        modifier = Modifier.padding(top = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(3) { idx ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Star",
                                tint = if (idx < stars) Color(0xFFFBBF24) else Color.White.copy(alpha = 0.15f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            // Indicator symbol (flash/lock icon)
            Text(
                text = if (isUnlocked) "⚡" else "🔒",
                color = if (isUnlocked) color else Color.White.copy(alpha = 0.3f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
