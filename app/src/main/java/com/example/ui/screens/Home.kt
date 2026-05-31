package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.SoundManager
import com.example.StateManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.GlassCard
import com.example.ui.components.LanguageSelector
import com.example.ui.components.NeonButton
import com.example.ui.components.RealmThemeToggle
import com.example.ui.theme.FantasyTheme

@Composable
fun HomeScreen(
    theme: FantasyTheme,
    stateManager: StateManager,
    onStartAdventure: () -> Unit,
    onExit: () -> Unit
) {
    val currentThemeName by stateManager.theme.collectAsState()
    val currentLang by stateManager.language.collectAsState()
    val totalXp by stateManager.totalXp.collectAsState()
    val playerLevel by stateManager.playerLevel.collectAsState()
    val completedQuizzes by stateManager.completedQuizzes.collectAsState()
    val unlockedGrades by stateManager.unlockedGrades.collectAsState()

    val isSinhala = currentLang == "si"
    var isMuted by remember { mutableStateOf(SoundManager.isMuted()) }

    // Start background music automatically when home screen loads
    LaunchedEffect(Unit) {
        SoundManager.startBackgroundMusic()
    }

    // Localized Strings Map
    // QUEST LOBBY should stay in English for both languages as per user constraint.
    val titleText = "QUEST LOBBY"
    val subtitleText = if (isSinhala) "ඔබේ ඉගෙනීමේ පියස" else "Hero's Sanctuary"
    val heroStatsLabel = if (isSinhala) "වීරයාගේ තත්ත්වය" else "HERO STATUS"
    val levelLabel = if (isSinhala) "මට්ටම" else "LEVEL"
    val xpLabel = if (isSinhala) "මුළු අත්දැකීම් ලකුණු (XP)" else "TOTAL XP"
    val completedQuizzesLabel = if (isSinhala) "සාර්ථකව නිමකළ විභාග" else "COMPLETED QUESTS"
    val unlockedGradesLabel = if (isSinhala) "මුදාහල ශ්‍රේණි" else "UNLOCKED REALMS"
    val realmToggleLabel = if (isSinhala) "විෂය දිශාව තෝරන්න" else "Select Learning Realm"
    val startBtnText = if (isSinhala) "ත්‍රාසජනක ගමන අරඹන්න" else "START ADVENTURE"
    val exitBtnText = if (isSinhala) "යෙදුමෙන් පිටවීම" else "EXIT GAME"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen_root")
    ) {
        // Star particle background
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
            // Header bar containing Title, Language Selector, and Mute Control Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = titleText,
                        color = theme.primary,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.testTag("home_title")
                    )
                    Text(
                        text = subtitleText,
                        color = theme.accent,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily.SansSerif
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Mute icon toggle button
                    IconButton(
                        onClick = {
                            SoundManager.toggleMute()
                            isMuted = SoundManager.isMuted()
                            if (!isMuted) {
                                SoundManager.startBackgroundMusic()
                            }
                        },
                        modifier = Modifier
                            .size(44.dp)
                            .testTag("mute_toggle_btn")
                    ) {
                        Icon(
                            imageVector = if (isMuted) Icons.Default.VolumeMute else Icons.Default.VolumeUp,
                            contentDescription = if (isMuted) "Unmute" else "Mute",
                            tint = theme.accent,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    LanguageSelector(
                        currentLang = currentLang,
                        onLangSelected = { stateManager.switchLanguage(it) }
                    )
                }
            }

            // Hero Avatar and Level Banner
            GlassCard(
                theme = theme,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("hero_status_card")
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Level Insignia Circle
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(36.dp))
                            .background(
                                Brush.radialGradient(
                                    colors = listOf(theme.portalColor, theme.secondary)
                                )
                            )
                            .border(2.dp, theme.accent, RoundedCornerShape(36.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = levelLabel.first().toString(),
                                color = Color.White.copy(alpha = 0.7f),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "$playerLevel",
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }

                    // Stats breakdown
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = heroStatsLabel,
                            color = theme.accent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "XP",
                                tint = Color(0xFFFBBF24),
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "$xpLabel: $totalXp",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.EmojiEvents,
                                contentDescription = "Completed Quests",
                                tint = theme.portalColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "$completedQuizzesLabel: $completedQuizzes",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }

            // World Map Quest Overview visual block
            GlassCard(
                theme = theme,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = "Map icon",
                        tint = theme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                    Column {
                        Text(
                            text = unlockedGradesLabel,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = if (isSinhala) {
                                "ශ්‍රේණි ${unlockedGrades.sorted().joinToString(", ")} දැනට විවෘතයි"
                            } else {
                                "Grades ${unlockedGrades.sorted().joinToString(", ")} unlocked and ready"
                            },
                            color = theme.accent,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Dynamic Realm Themes Toggle
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = realmToggleLabel,
                    color = theme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
                
                RealmThemeToggle(
                    currentThemeName = currentThemeName,
                    onThemeToggle = { stateManager.switchTheme(it) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Main CTA start Adventure button
            NeonButton(
                text = startBtnText,
                theme = theme,
                onClick = {
                    SoundManager.playClick()
                    onStartAdventure()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .testTag("start_adventure_btn")
            )

            // Sleek EXIT button to quit the app instantly
            TextButton(
                onClick = {
                    SoundManager.playClick()
                    onExit()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("exit_game_btn")
            ) {
                Text(
                    text = exitBtnText.uppercase(),
                    color = theme.accent.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}
