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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.GameViewModel
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme

@Composable
fun GradeSelect(
    viewModel: GameViewModel,
    onGradeSelected: () -> Unit
) {
    val theme = LocalFantasyTheme
    val lang by viewModel.language.collectAsState()
    val isSinhala = lang == "Sinhala"

    val titleText = if (isSinhala) "ශ්‍රේණිය තෝරන්න" else "SELECT GRADE"
    val subtitleText = if (isSinhala) "ඔබේ දැනුමේ පියවර තෝරන්න" else "SELECT LEVEL OF MATURITY"

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

            listOf(3, 4, 5).forEach { g ->
                val btnLabel = if (isSinhala) "ශ්‍රේණිය $g ⭐" else "GRADE $g ⭐"
                FantasyButton(
                    text = btnLabel,
                    onClick = {
                        viewModel.selectGrade(g)
                        onGradeSelected()
                    },
                    accentColor = theme.accent,
                    testTag = "grade_${g}_button"
                )
            }
        }
    }
}
