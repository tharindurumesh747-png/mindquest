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
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Science
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.StateManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.GlassCard
import com.example.ui.theme.FantasyTheme

@Composable
fun SubjectSelectScreen(
    grade: Int,
    theme: FantasyTheme,
    stateManager: StateManager,
    onSubjectSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    val currentLang by stateManager.language.collectAsState()
    val isSinhala = currentLang == "si"

    // Localized labels
    val titleText = if (isSinhala) "විෂයය තෝරන්න" else "CHOOSE SUBJECT"
    val subtitleText = if (isSinhala) "${grade} ශ්‍රේණිය සඳහා වන ඉගෙනුම් අභියෝගය" else "Select your quest for Grade $grade"
    
    // Subject localisations
    val mathTitle = if (isSinhala) "ගණිතය" else "Math"
    val mathDesc = if (isSinhala) "විජ්ජා අංක ගණිත ලිත්" else "The Wizard's Arithmancy"

    val scienceTitle = if (isSinhala) "විද්‍යාව" else "Science"
    val scienceDesc = if (isSinhala) "රසායනික පර්යේෂණාගාර රහස්" else "The Alchemist's Cauldron"

    val englishTitle = if (isSinhala) "ඉංග්‍රීසි" else "English"
    val englishDesc = if (isSinhala) "කාව්‍යමය අක්ෂර සහ ව්‍යාකරණ" else "The Bard's Ancient Runes"

    val historyTitle = if (isSinhala) "ඉතිහාසය" else "History"
    val historyDesc = if (isSinhala) "පුරාවෘත්ත වංශකථා සහ රජවරු" else "The Archivist's Chronology"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("subject_select_root")
    ) {
        // Star field background
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
                        modifier = Modifier.testTag("subject_select_title")
                    )
                    Text(
                        text = subtitleText,
                        color = theme.primary,
                        fontSize = 12.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submissions List
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SubjectQuestCard(
                    title = mathTitle,
                    description = mathDesc,
                    icon = Icons.Default.Calculate,
                    accentColor = Color(0xFFFBBF24), // Gold glow
                    theme = theme,
                    onClick = { onSubjectSelected("Math") }
                )

                SubjectQuestCard(
                    title = scienceTitle,
                    description = scienceDesc,
                    icon = Icons.Default.Science,
                    accentColor = Color(0xFF10B981), // Emerald glow
                    theme = theme,
                    onClick = { onSubjectSelected("Science") }
                )

                SubjectQuestCard(
                    title = englishTitle,
                    description = englishDesc,
                    icon = Icons.Default.AutoStories,
                    accentColor = Color(0xFFC084FC), // Violet glow
                    theme = theme,
                    onClick = { onSubjectSelected("English") }
                )

                SubjectQuestCard(
                    title = historyTitle,
                    description = historyDesc,
                    icon = Icons.Default.HistoryEdu,
                    accentColor = Color(0xFFFB923C), // Amber-orange glow
                    theme = theme,
                    onClick = { onSubjectSelected("History") }
                )
            }
        }
    }
}

@Composable
fun SubjectQuestCard(
    title: String,
    description: String,
    icon: ImageVector,
    accentColor: Color,
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
                color = accentColor.copy(alpha = 0.5f),
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
            // Icon backdrop with custom colors
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(accentColor.copy(alpha = 0.15f))
                    .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "$title icon",
                    tint = accentColor,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Text content
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title.uppercase(),
                    color = Color.White,
                    fontSize = 18.sp,
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

            // Arrow forward indicator
            Text(
                text = "➢",
                color = accentColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
