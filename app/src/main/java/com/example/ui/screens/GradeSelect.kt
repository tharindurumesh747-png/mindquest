package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.StateManager
import com.example.ui.components.AnimatedStarField
import com.example.ui.components.GlassCard
import com.example.ui.theme.FantasyTheme

@Composable
fun GradeSelectScreen(
    theme: FantasyTheme,
    stateManager: StateManager,
    onGradeSelected: (Int) -> Unit,
    onBack: () -> Unit
) {
    val currentLang by stateManager.language.collectAsState()
    val unlockedGrades by stateManager.unlockedGrades.collectAsState()

    val isSinhala = currentLang == "si"
    
    val titleText = if (isSinhala) "ශ්‍රේණි තෝරන්න" else "SELECT GRADE"
    val subtitleText = if (isSinhala) "දේශනයේ ද්වාරය තෝරන්න" else "Choose your educational battleground (1-10)"
    val lockedWarning = if (isSinhala) "පෙර එක් පියවරක් නිමා කරන්න" else "Complete previous grade to unlock!"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("grade_select_root")
    ) {
        // Animated space stars
        AnimatedStarField(theme = theme)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(16.dp)
        ) {
            // Header back button & title
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.testTag("back_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Go back",
                        tint = theme.accent
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))
                
                Column {
                    Text(
                        text = titleText,
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.testTag("grade_select_title")
                    )
                    Text(
                        text = subtitleText,
                        color = theme.primary,
                        fontSize = 11.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Scrollable Grid of Grades 1-10
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("grade_grid")
            ) {
                items((1..10).toList()) { grade ->
                    val isLocked = !unlockedGrades.contains(grade)
                    
                    GradePortalCard(
                        grade = grade,
                        isLocked = isLocked,
                        theme = theme,
                        isSinhala = isSinhala,
                        onClick = {
                            if (!isLocked) {
                                onGradeSelected(grade)
                            }
                        }
                    )
                }
            }

            // Bottom locking feedback warning
            Text(
                text = lockedWarning,
                color = theme.accent.copy(alpha = 0.5f),
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }
    }
}

@Composable
fun GradePortalCard(
    grade: Int,
    isLocked: Boolean,
    theme: FantasyTheme,
    isSinhala: Boolean,
    onClick: () -> Unit
) {
    val titleLabel = if (isSinhala) "ශ්‍රේණිය" else "GRADE"
    val stateLabel = if (isLocked) "මඟ හරින්න" else "විවෘතයි" // Simplified locks feedback

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isLocked) Color.Black.copy(alpha = 0.6f) else theme.surface.copy(alpha = 0.4f)
            )
            .border(
                width = 1.dp,
                color = if (isLocked) Color.White.copy(alpha = 0.1f) else theme.primary.copy(alpha = 0.6f),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon representing lock status
            Icon(
                imageVector = if (isLocked) Icons.Default.Lock else Icons.Default.LockOpen,
                contentDescription = if (isLocked) "Locked portal" else "Unlocked portal",
                tint = if (isLocked) Color.Gray else theme.accent,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = "$titleLabel $grade",
                color = if (isLocked) Color.Gray else Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )

            Text(
                text = if (isLocked) "LOCKED" else "UNLOCKED",
                color = if (isLocked) Color.Gray.copy(alpha = 0.7f) else theme.accent,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}
