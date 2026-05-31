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
import androidx.compose.material.icons.filled.MilitaryTech
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.StateManager
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
                    onClick = { onLevelSelected(1) }
                )

                LevelStageCard(
                    level = 2,
                    title = level2Title,
                    description = level2Desc,
                    color = theme.secondary,
                    theme = theme,
                    onClick = { onLevelSelected(2) }
                )

                LevelStageCard(
                    level = 3,
                    title = level3Title,
                    description = level3Desc,
                    color = theme.portalColor,
                    theme = theme,
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
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(theme.surface.copy(alpha = 0.4f))
            .border(
                width = 1.dp,
                color = color.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Level Badge Block
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(color.copy(alpha = 0.15f))
                    .border(2.dp, color, RoundedCornerShape(26.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MilitaryTech,
                    contentDescription = "Stage badge",
                    tint = color,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Texts
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = description,
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }

            // Forward Chevron Symbol
            Text(
                text = "⚡",
                color = color,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
