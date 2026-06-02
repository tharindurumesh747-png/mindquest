package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ui.screens.*
import com.example.ui.theme.MindQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize SoundManager with attribution context
        val attributionContext = if (android.os.Build.VERSION.SDK_INT >= 30) {
            createAttributionContext("default")
        } else {
            applicationContext
        }
        SoundManager.init(attributionContext)

        // Register process lifecycle callbacks to track when the app state changes
        try {
            ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onStart(owner: LifecycleOwner) {
                    super.onStart(owner)
                    SoundManager.resumeMusic()
                }

                override fun onStop(owner: LifecycleOwner) {
                    super.onStop(owner)
                    SoundManager.pauseMusic()
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            MindQuestTheme {
                val gameViewModel: GameViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") {
                        SplashScreen(
                            onNavigateHome = {
                                navController.navigate("home") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable("home") {
                        Home(
                            viewModel = gameViewModel,
                            onNavigateStart = {
                                navController.navigate("grade_select")
                            },
                            onNavigateSettings = {
                                navController.navigate("settings")
                            },
                            onExitApp = {
                                finishAffinity()
                            }
                        )
                    }
                    composable("grade_select") {
                        GradeSelect(
                            viewModel = gameViewModel,
                            onGradeSelected = {
                                navController.navigate("subject_select")
                            }
                        )
                    }
                    composable("subject_select") {
                        SubjectSelect(
                            viewModel = gameViewModel,
                            onSubjectSelected = {
                                navController.navigate("level_select")
                            }
                        )
                    }
                    composable("level_select") {
                        LevelSelect(
                            viewModel = gameViewModel,
                            onLevelSelected = {
                                gameViewModel.resetQuizState()
                                navController.navigate("quiz")
                            }
                        )
                    }
                    composable("quiz") {
                        QuizScreen(
                            viewModel = gameViewModel,
                            onNavigateResults = {
                                navController.navigate("results") {
                                    popUpTo("quiz") { inclusive = true }
                                }
                            },
                            onNavigateBackToHome = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                    }
                    composable("results") {
                        ResultsScreen(
                            viewModel = gameViewModel,
                            onNavigatePlayAgain = {
                                navController.navigate("quiz") {
                                    popUpTo("results") { inclusive = true }
                                }
                            },
                            onNavigateBackToHome = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                    }
                    composable("settings") {
                        SettingsScreen(
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        SoundManager.stopAllSounds()
    }

    override fun onDestroy() {
        super.onDestroy()
        SoundManager.stopAllSounds()
        SoundManager.release()
    }
}
