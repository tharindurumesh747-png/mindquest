package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.FantasyTheme
import kotlin.random.Random

// Animated deep space starlight background
@Composable
fun AnimatedStarField(
    theme: FantasyTheme,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")

    // Dynamic scale oscillation for stars pulsing glow
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    // Remember random coordinates for 35 star particles
    val starPositions = remember {
        List(35) {
            Offset(
                x = Random.nextFloat(),
                y = Random.nextFloat()
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        theme.background,
                        Color(0xFF030712)
                    )
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Star draw loops
            starPositions.forEachIndexed { index, pos ->
                val sizeVal = (3f + (index % 5) * 2f) * if (index % 2 == 0) pulseScale else (1.4f - pulseScale)
                val alpha = (0.3f + (index % 10) * 0.07f) * if (index % 3 == 0) pulseScale else 0.8f
                
                drawCircle(
                    color = theme.accent.copy(alpha = alpha),
                    radius = sizeVal,
                    center = Offset(pos.x * width, pos.y * height)
                )
            }
        }
    }
}

// Glassmorphism Card Container
@Composable
fun GlassCard(
    theme: FantasyTheme,
    modifier: Modifier = Modifier,
    borderWidth: Dp = 1.dp,
    cornerRadius: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(theme.surface.copy(alpha = 0.5f))
            .border(
                width = borderWidth,
                brush = Brush.linearGradient(
                    colors = listOf(
                        theme.primary.copy(alpha = 0.6f),
                        theme.secondary.copy(alpha = 0.1f)
                    )
                ),
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(16.dp)
    ) {
        Column {
            content()
        }
    }
}

// Neon Glow RPG Styled Button
@Composable
fun NeonButton(
    text: String,
    theme: FantasyTheme,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    testTag: String = ""
) {
    val infiniteTransition = rememberInfiniteTransition(label = "neon_glow")
    
    // Animate glowing border depth
    val borderGlow by infiniteTransition.animateFloat(
        initialValue = 2.dp.value,
        targetValue = 6.dp.value,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_depth"
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .then(
                if (enabled) {
                    Modifier.background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                theme.secondary,
                                theme.primary
                            )
                        )
                    )
                } else {
                    Modifier.background(Color.Gray.copy(alpha = 0.3f))
                }
            )
            .clickable(enabled = enabled, onClick = onClick)
            .border(
                width = if (enabled) borderGlow.dp else 1.dp,
                color = if (enabled) theme.accent.copy(alpha = 0.6f) else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            textAlign = TextAlign.Center,
            modifier = if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier
        )
    }
}

// RPG Health hearts representation bar
@Composable
fun HeartsRepresentationBar(
    lives: Int,
    maxLives: Int = 3
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(maxLives) { index ->
            val isFilled = index < lives
            Icon(
                imageVector = if (isFilled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Life heart",
                tint = if (isFilled) Color(0xFFEF4444) else Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// Language selector floating pills selector
@Composable
fun LanguageSelector(
    currentLang: String,
    onLangSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.Black.copy(alpha = 0.4f))
            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (currentLang == "en") Color.White.copy(alpha = 0.2f) else Color.Transparent)
                .clickable { onLangSelected("en") }
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text("EN", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (currentLang == "si") Color.White.copy(alpha = 0.2f) else Color.Transparent)
                .clickable { onLangSelected("si") }
                .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text("සිං", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
    }
}

// Custom theme selector for our Realms of Learning
@Composable
fun RealmThemeToggle(
    currentThemeName: String,
    onThemeToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val realms = listOf("Cosmic Blue", "Magical Forest", "Volcanic Forge")
    
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black.copy(alpha = 0.5f))
            .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        realms.forEach { realm ->
            val isSelected = realm == currentThemeName
            val bg = when (realm) {
                "Cosmic Blue" -> Color(0xFF1E3A8A)
                "Magical Forest" -> Color(0xFF064E3B)
                "Volcanic Forge" -> Color(0xFF7F1D1D)
                else -> Color.DarkGray
            }
            
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isSelected) bg else Color.Transparent)
                    .clickable { onThemeToggle(realm) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = when(realm) {
                        "Cosmic Blue" -> "🌌 Cosmic"
                        "Magical Forest" -> "🌲 Elven"
                        "Volcanic Forge" -> "🌋 Volcanic"
                        else -> realm
                    },
                    color = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }
    }
}
