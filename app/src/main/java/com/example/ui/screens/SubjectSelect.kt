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
import androidx.compose.ui.unit.sp
import com.example.GameViewModel
import com.example.ui.components.FantasyButton
import com.example.ui.components.FantasyTitle
import com.example.ui.theme.LocalFantasyTheme

@Composable
fun SubjectSelect(
    viewModel: GameViewModel,
    onSubjectSelected: () -> Unit
) {
    val theme = LocalFantasyTheme
    val lang by viewModel.language.collectAsState()
    val isSinhala = lang == "Sinhala"

    val titleText = if (isSinhala) "විෂය තෝරන්න" else "SELECT SUBJECT"
    val subtitleText = if (isSinhala) "දැනුම් කලාපය තෝරන්න" else "CHOOSE YOUR REALM"

    val subjects = listOf("Science", "Math", "English", "History")

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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FantasyTitle(text = titleText, subtitle = subtitleText)

            Spacer(modifier = Modifier.height(16.dp))

            subjects.forEach { subj ->
                val label = when (subj) {
                    "Science" -> if (isSinhala) "විද්‍යාව 🔬" else "SCIENCE 🔬"
                    "Math" -> if (isSinhala) "ගණිතය 📐" else "MATHEMATICS 📐"
                    "English" -> if (isSinhala) "ඉංග්‍රීසි 📚" else "ENGLISH GRAMMAR 📚"
                    else -> if (isSinhala) "ඉතිහාසය 🏛️" else "HISTORY 🏛️"
                }

                FantasyButton(
                    text = label,
                    onClick = {
                        viewModel.selectSubject(subj)
                        onSubjectSelected()
                    },
                    accentColor = theme.accent,
                    testTag = "subject_${subj.lowercase()}_button"
                )
            }
        }
    }
}
