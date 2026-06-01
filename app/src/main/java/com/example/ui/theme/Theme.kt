package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = LightPortal,
    secondary = PortalViolet,
    tertiary = WizardAccent,
    background = DeepSpaceDb,
    surface = CosmicSlate,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

class FantasyGameThemeColors {
    val primary = LightPortal
    val secondary = PortalViolet
    val accent = WizardAccent
    val surface = CosmicSlate
    val background = DeepSpaceDb
    val portalColor = PortalViolet
    val successColor = LeafSuccess
    val failColor = BloodFail
    val infoColor = BrightCyan
    val gold = AntiqueGold
}

val LocalFantasyTheme = FantasyGameThemeColors()

@Composable
fun MindQuestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
