package com.example.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.SoundManager
import com.example.data.SoundSettings
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyCard
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit
) {
    val theme = LocalFantasyTheme
    val context = LocalContext.current

    // Local mutable states synced with SoundSettings to trigger Compose recomposition in real time
    var masterVol by remember { mutableFloatStateOf(SoundSettings.masterVolume) }
    var bgEnabled by remember { mutableStateOf(SoundSettings.backgroundMusicEnabled) }
    var btnEnabled by remember { mutableStateOf(SoundSettings.buttonSoundEnabled) }
    var correctEnabled by remember { mutableStateOf(SoundSettings.correctSoundEnabled) }
    var wrongEnabled by remember { mutableStateOf(SoundSettings.wrongSoundEnabled) }
    var navEnabled by remember { mutableStateOf(SoundSettings.navigationSoundEnabled) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(theme.background, Color(0xFF0F0924))
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header with Back arrow button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        SoundManager.playNavigation()
                        onNavigateBack()
                    },
                    modifier = Modifier.testTag("settings_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigate back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "SETTINGS",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            FantasyTitle(text = "SOUND SETTINGS", subtitle = "MANAGE YOUR AUDIO SPELLS")

            Spacer(modifier = Modifier.height(8.dp))

            // Settings Controls Card
            FantasyCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 🔊 Master Volume Slider
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🔊 Master Volume",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "${(masterVol * 100).toInt()}%",
                            color = theme.accent,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                    Slider(
                        value = masterVol,
                        onValueChange = { newValue ->
                            masterVol = newValue
                            SoundSettings.masterVolume = newValue // Real-time immediate effect
                        },
                        valueRange = 0f..1f,
                        colors = SliderDefaults.colors(
                            thumbColor = theme.accent,
                            activeTrackColor = theme.primary,
                            inactiveTrackColor = Color.White.copy(alpha = 0.2f)
                        ),
                        modifier = Modifier.testTag("master_volume_slider")
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color.White.copy(alpha = 0.1f))
                Spacer(modifier = Modifier.height(12.dp))

                // Toggle helper builder
                val buildToggle: @Composable (String, Boolean, (Boolean) -> Unit, String) -> Unit = 
                    { title, checked, onCheckedChange, tag ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = title,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                            Switch(
                                checked = checked,
                                onCheckedChange = { newVal ->
                                    SoundManager.playButtonTap()
                                    onCheckedChange(newVal)
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = theme.accent,
                                    checkedTrackColor = theme.primary,
                                    uncheckedThumbColor = Color.DarkGray,
                                    uncheckedTrackColor = Color.White.copy(alpha = 0.15f)
                                ),
                                modifier = Modifier.testTag(tag)
                            )
                        }
                    }

                // Toggles stack
                buildToggle(
                    "🎵 Background Music", 
                    bgEnabled, 
                    { 
                        bgEnabled = it
                        SoundSettings.backgroundMusicEnabled = it 
                    },
                    "bg_music_switch"
                )

                buildToggle(
                    "👆 Button Sounds", 
                    btnEnabled, 
                    { 
                        btnEnabled = it
                        SoundSettings.buttonSoundEnabled = it 
                    },
                    "button_sounds_switch"
                )

                buildToggle(
                    "✅ Correct Answer", 
                    correctEnabled, 
                    { 
                        correctEnabled = it
                        SoundSettings.correctSoundEnabled = it 
                    },
                    "correct_sounds_switch"
                )

                buildToggle(
                    "❌ Wrong Answer", 
                    wrongEnabled, 
                    { 
                        wrongEnabled = it
                        SoundSettings.wrongSoundEnabled = it 
                    },
                    "wrong_sounds_switch"
                )

                buildToggle(
                    "🧭 Navigation Sound", 
                    navEnabled, 
                    { 
                        navEnabled = it
                        SoundSettings.navigationSoundEnabled = it 
                    },
                    "nav_sounds_switch"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons Stack: Save Settings & Reset to Defaults
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // RESET TO DEFAULT button
                Button(
                    onClick = {
                        SoundManager.playButtonTap()
                        SoundSettings.loadDefaults(context)
                        // Sync UI with defaults
                        masterVol = SoundSettings.masterVolume
                        bgEnabled = SoundSettings.backgroundMusicEnabled
                        btnEnabled = SoundSettings.buttonSoundEnabled
                        correctEnabled = SoundSettings.correctSoundEnabled
                        wrongEnabled = SoundSettings.wrongSoundEnabled
                        navEnabled = SoundSettings.navigationSoundEnabled
                        Toast.makeText(context, "Settings reset to defaults!", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = theme.surface.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(25.dp)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("RESET DEFAULTS", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                // SAVE SETTINGS button
                Button(
                    onClick = {
                        SoundManager.playButtonTap()
                        SoundSettings.save(context)
                        Toast.makeText(context, "Settings Saved successfully!", Toast.LENGTH_SHORT).show()
                        onNavigateBack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = theme.primary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(1.dp, theme.accent.copy(alpha = 0.5f), RoundedCornerShape(25.dp))
                        .testTag("save_settings_button"),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("SAVE SETTINGS", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
