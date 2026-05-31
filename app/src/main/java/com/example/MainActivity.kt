package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.*
import com.example.ui.theme.FantasyThemes

sealed class Screen {
    object Splash : Screen()
    object Home : Screen()
    object GradeSelect : Screen()
    object SubjectSelect : Screen()
    object LevelSelect : Screen()
    object Quiz : Screen()
    object Result : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val gameViewModel: GameViewModel = viewModel()
            val currentThemeName by gameViewModel.stateManager.theme.collectAsState()
            val theme = FantasyThemes.getTheme(currentThemeName)

            // Progressive state navigation layout
            val screenStack = remember { mutableStateListOf<Screen>(Screen.Splash) }
            val currentScreen = screenStack.lastOrNull() ?: Screen.Splash

            fun navigateTo(screen: Screen) {
                screenStack.add(screen)
            }

            fun navigateBack() {
                if (screenStack.size > 1) {
                    screenStack.removeAt(screenStack.lastIndex)
                }
            }

            // Handle system-level physical Back buttons on low-end phones
            BackHandler(enabled = screenStack.size > 1) {
                navigateBack()
            }

            MaterialTheme {
                Crossfade(
                    targetState = currentScreen,
                    modifier = Modifier.fillMaxSize(),
                    label = "navigation"
                ) { screen ->
                    when (screen) {
                        Screen.Splash -> {
                            SplashScreen(
                                theme = theme,
                                onEnterPortal = {
                                    screenStack.clear()
                                    screenStack.add(Screen.Home)
                                }
                            )
                        }

                        Screen.Home -> {
                            HomeScreen(
                                theme = theme,
                                stateManager = gameViewModel.stateManager,
                                onStartAdventure = {
                                    navigateTo(Screen.GradeSelect)
                                },
                                onExit = {
                                    finishAffinity()
                                }
                            )
                        }

                        Screen.GradeSelect -> {
                            GradeSelectScreen(
                                theme = theme,
                                stateManager = gameViewModel.stateManager,
                                onGradeSelected = { grade ->
                                    gameViewModel.selectGrade(grade)
                                    navigateTo(Screen.SubjectSelect)
                                },
                                onBack = { navigateBack() }
                            )
                        }

                        Screen.SubjectSelect -> {
                            val selectedGrade by gameViewModel.selectedGrade.collectAsState()
                            SubjectSelectScreen(
                                grade = selectedGrade,
                                theme = theme,
                                stateManager = gameViewModel.stateManager,
                                onSubjectSelected = { subject ->
                                    gameViewModel.selectSubject(subject)
                                    navigateTo(Screen.LevelSelect)
                                },
                                onBack = { navigateBack() }
                            )
                        }

                        Screen.LevelSelect -> {
                            val selectedGrade by gameViewModel.selectedGrade.collectAsState()
                            val selectedSubject by gameViewModel.selectedSubject.collectAsState()
                            LevelSelectScreen(
                                grade = selectedGrade,
                                subject = selectedSubject,
                                theme = theme,
                                stateManager = gameViewModel.stateManager,
                                onLevelSelected = { level ->
                                    gameViewModel.selectLevel(level)
                                    gameViewModel.startQuiz()
                                    navigateTo(Screen.Quiz)
                                },
                                onBack = { navigateBack() }
                            )
                        }

                        Screen.Quiz -> {
                            QuizScreen(
                                viewModel = gameViewModel,
                                theme = theme,
                                onQuizFinished = {
                                    screenStack.remove(Screen.Quiz)
                                    navigateTo(Screen.Result)
                                },
                                onBack = {
                                    navigateBack()
                                }
                            )
                        }

                        Screen.Result -> {
                            val selectedGrade by gameViewModel.selectedGrade.collectAsState()
                            val selectedSubject by gameViewModel.selectedSubject.collectAsState()
                            val selectedLevel by gameViewModel.selectedLevel.collectAsState()
                            val score by gameViewModel.score.collectAsState()
                            val earnedXp by gameViewModel.earnedXp.collectAsState()

                            val nextLevel = selectedLevel + 1
                            val isNextLevelAvailable = nextLevel <= 3

                            val onNextLevelAction: (() -> Unit)? = if (isNextLevelAvailable) {
                                {
                                    gameViewModel.selectLevel(nextLevel)
                                    gameViewModel.startQuiz()
                                    screenStack.remove(Screen.Result)
                                    navigateTo(Screen.Quiz)
                                }
                            } else null

                            ResultScreen(
                                score = score,
                                earnedXp = earnedXp,
                                grade = selectedGrade,
                                subject = selectedSubject,
                                level = selectedLevel,
                                theme = theme,
                                stateManager = gameViewModel.stateManager,
                                onRetry = {
                                    gameViewModel.startQuiz()
                                    screenStack.remove(Screen.Result)
                                    navigateTo(Screen.Quiz)
                                },
                                onNextLevel = onNextLevelAction,
                                onReturnToMap = {
                                    screenStack.clear()
                                    screenStack.add(Screen.Home)
                                    screenStack.add(Screen.GradeSelect)
                                    screenStack.add(Screen.SubjectSelect)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
