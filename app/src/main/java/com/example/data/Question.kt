package com.example.data

data class Question(
    val id: String,
    val question: String,
    val questionSinhala: String,
    val options: List<String>,
    val optionsSinhala: List<String>,
    val correctAnswer: Int, // index 0-3
    val grade: Int,
    val subject: String,
    val stage: Int, // 1, 2, or 3
    val difficulty: String, // easy/medium/hard
    val hint: String,
    val hintSinhala: String
) {
    // Backward compatibility getters for previous screens/methods
    val text: String get() = question
    val correctAnswerIndex: Int get() = correctAnswer
    val questionId: String get() = id
}
