package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.NeonButton
import com.example.ui.theme.FantasyTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    theme: FantasyTheme,
    onEnterPortal: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "portal")

    // Rotation animation for the portal outer rings
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "portal_rotate"
    )

    // Pulsing animation for the magical portal opening
    val scalePulse by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "portal_pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("splash_screen_root"),
        contentAlignment = Alignment.Center
    ) {
        // Animated background starlight
        AnimatedStarField(theme = theme)

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            // Game Name
            Text(
                text = "MINDQUEST",
                color = theme.primary,
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                letterSpacing = 4.sp,
                modifier = Modifier.testTag("splash_title")
            )

            Text(
                text = "Fantasy RPG Learning Realm",
                color = theme.accent,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(top = 4.dp, bottom = 48.dp)
            )

            // Animated Visual Portal Circle
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .scale(scalePulse)
                    .rotate(rotation)
                    .border(
                        width = 4.dp,
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                theme.primary,
                                theme.accent,
                                theme.secondary,
                                theme.portalColor,
                                theme.primary
                            )
                        ),
                        shape = CircleShape
                    )
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                theme.portalColor.copy(alpha = 0.4f),
                                Color.Transparent
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Outer swirling glow particles ring
                Box(
                    modifier = Modifier
                        .size(190.dp)
                        .rotate(-rotation * 1.5f)
                        .border(
                            width = 2.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(theme.portalColor, theme.accent)
                            ),
                            shape = CircleShape
                        )
                )
                
                // Portal center text core
                Text(
                    text = "✧",
                    color = theme.accent,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(56.dp))

            // Adventure Entry Button
            NeonButton(
                text = "ENTER LEARNING PORTAL",
                theme = theme,
                onClick = onEnterPortal,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .testTag("enter_portal_btn")
            )
        }
    }
}
