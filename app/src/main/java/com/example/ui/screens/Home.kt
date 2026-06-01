package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
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
import com.example.SoundManager
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyCard
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme

@Composable
fun Home(
    viewModel: GameViewModel,
    onNavigateStart: () -> Unit,
    onNavigateSettings: () -> Unit,
    onExitApp: () -> Unit
) {
    val theme = LocalFantasyTheme
    val currentLanguage by viewModel.language.collectAsState()
    val isSinhala = currentLanguage == "Sinhala"

    val titleText = if (isSinhala) "මයින්ඩ් ක්වෙස්ට්" else "MINDQUEST"
    val subtitleText = if (isSinhala) "ඉගෙනීමේ විජ්ජා රාජධානිය" else "REALM OF KNOWLEDGE"
    val startQuestLabel = if (isSinhala) "වික්‍රමය අරඹන්න ⚔️" else "START QUEST ⚔️"
    val settingsLabel = if (isSinhala) "ශබ්ද සැකසුම් ⚙️" else "SOUND SETTINGS ⚙️"
    val exitLabel = if (isSinhala) "පිටවීම 🚪" else "EXIT GAME 🚪"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(theme.background, Color(0xFF0C071C))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Upper Deck Widget: Language Selection Badge
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.Black.copy(alpha = 0.5f))
                    .border(1.dp, theme.portalColor.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf("English", "Sinhala").forEach { lang ->
                    val isSelected = currentLanguage == lang
                    val displayText = if (lang == "Sinhala") "සිංහල" else "English"
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) theme.primary else Color.Transparent)
                            .clickable {
                                SoundManager.playButtonTap()
                                viewModel.setLanguage(lang)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = displayText,
                            color = if (isSelected) Color.White else Color.Gray,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Main RPG Title Header
            FantasyTitle(
                text = titleText,
                subtitle = subtitleText
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Inner core banner
            FantasyCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (isSinhala) 
                        "රාජධානියේ ප්‍රශ්නවලට නිවැරදිව පිළිතුරු සපයා, ලකුණු රැස්කර ප්‍රමුඛයා වන්න!" 
                        else "Journey through the ancient scrolls of knowledge, match correct answers, and unleash magical streaks!",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Play Trigger Spells Buttons Stack
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FantasyButton(
                    text = startQuestLabel,
                    onClick = { onNavigateStart() },
                    accentColor = theme.accent,
                    testTag = "start_quest_button"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Settings Button
                    Button(
                        onClick = {
                            SoundManager.playNavigation()
                            onNavigateSettings()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = theme.surface.copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                            .border(1.dp, theme.portalColor.copy(alpha = 0.5f), RoundedCornerShape(27.dp)),
                        shape = RoundedCornerShape(27.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Gear settings icon button",
                            tint = theme.accent
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSinhala) "සැකසුම්" else "SETTINGS",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Exit action button with cleanup
                    Button(
                        onClick = {
                            SoundManager.playButtonTap() 
                            // Tap Exit plays action instantly
                            SoundManager.stopAllSounds()
                            SoundManager.release()
                            onExitApp()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3B0F15).copy(alpha = 0.9f)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                            .border(1.dp, Color.Red.copy(alpha = 0.5f), RoundedCornerShape(27.dp))
                            .testTag("exit_game_button"),
                        shape = RoundedCornerShape(27.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Door sign exit app",
                            tint = Color.Red
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSinhala) "පිටවීම" else "EXIT",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
