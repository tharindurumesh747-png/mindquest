package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.SoundManager
import com.example.ui.theme.LocalFantasyTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateHome: () -> Unit) {
    val theme = LocalFantasyTheme
    
    val scale = remember { Animatable(0.5f) }
    val opacity = remember { Animatable(0.0f) }

    LaunchedEffect(Unit) {
        // Play levelup/sparkle sound for a magical introduction
        SoundManager.playLevelUp()
        
        // Parallel launch animations
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
        opacity.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(1000)
        )
        
        delay(1500) // beautiful intro buffer
        onNavigateHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(theme.background, Color(0xFF07050E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = theme.accent,
                modifier = Modifier
                    .size(90.dp)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "MINDQUEST",
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                fontFamily = FontFamily.SansSerif,
                letterSpacing = 6.sp,
                modifier = Modifier.testTag("splash_title")
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The Magic of Learning",
                color = theme.portalColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 2.sp
            )
        }
    }
}
