package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.GameViewModel
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme

@Composable
fun LevelSelect(
    viewModel: GameViewModel,
    onLevelSelected: () -> Unit
) {
    val theme = LocalFantasyTheme
    val lang by viewModel.language.collectAsState()
    val isSinhala = lang == "Sinhala"

    val titleText = if (isSinhala) "අදියර තෝරන්න" else "SELECT STAGE"
    val subtitleText = if (isSinhala) "ඔබේ පාඨමාලා මට්ටම" else "SELECT CURRICULUM LEVEL"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(theme.background, Color(0xFF0F0924))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            FantasyTitle(text = titleText, subtitle = subtitleText)

            Spacer(modifier = Modifier.height(16.dp))

            listOf(1, 2, 3).forEach { lvl ->
                val btnLabel = if (isSinhala) "අදියර $lvl ✨" else "STAGE $lvl ✨"
                FantasyButton(
                    text = btnLabel,
                    onClick = {
                        viewModel.setStage(lvl)
                        onLevelSelected()
                    },
                    accentColor = theme.accent,
                    testTag = "stage_${lvl}_button"
                )
            }
        }
    }
}
