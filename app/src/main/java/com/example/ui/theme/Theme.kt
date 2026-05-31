package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Fantasy Theme data class representing various magical learning realms
data class FantasyTheme(
    val name: String,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val background: Color,
    val surface: Color,
    val glow: Color,
    val onBackground: Color = Color.White,
    val onSurface: Color = Color(0xFFF1F5F9),
    val portalColor: Color = Color(0xFFC084FC)
)

// Set of realms that players unlock and switch between
object FantasyThemes {
    val CosmicBlue = FantasyTheme(
        name = "Cosmic Blue",
        primary = Color(0xFF38BDF8), // Cyan sky
        secondary = Color(0xFF818CF8), // Indigo
        accent = Color(0xFF00FFCC), // Mythic seafoam neon
        background = Color(0xFF020617), // Deep space
        surface = Color(0xFF0F172A), // Slate vault
        glow = Color(0x7F38BDF8),
        portalColor = Color(0xFFC084FC)
    )

    val MagicalForest = FantasyTheme(
        name = "Magical Forest",
        primary = Color(0xFF34D399), // Emerald
        secondary = Color(0xFF10B981), // Leaf green
        accent = Color(0xFFA7F3D0), // Pastel elf glow
        background = Color(0xFF064E3B), // Elven forest depths
        surface = Color(0xFF065F46), // Shadow forest grove
        glow = Color(0x7F34D399),
        portalColor = Color(0xFFFBBF24)
    )

    val VolcanicForge = FantasyTheme(
        name = "Volcanic Forge",
        primary = Color(0xFFF87171), // Flame orange-red
        secondary = Color(0xFFEF4444), // Crimson
        accent = Color(0xFFFDE047), // Magma yellow
        background = Color(0xFF450A0A), // Core of the earth
        surface = Color(0xFF7F1D1D), // Molten iron chamber
        glow = Color(0x7FF87171),
        portalColor = Color(0xFFFB923C)
    )

    fun getTheme(name: String): FantasyTheme {
        return when (name) {
            "Cosmic Blue" -> CosmicBlue
            "Magical Forest" -> MagicalForest
            "Volcanic Forge" -> VolcanicForge
            else -> CosmicBlue
        }
    }
}

// Global Compose Dark Theme Configuration
private val DarkColorScheme = darkColorScheme(
    primary = CosmicGlowPrimary,
    secondary = CosmicGlowSecondary,
    tertiary = CosmicNeonAccent,
    background = CosmicDeepSpace,
    surface = CosmicDarkSurface,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = CosmicTextWhite,
    onSurface = CosmicTextWhite
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
