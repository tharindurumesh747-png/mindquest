package com.example.data

data class Question(
    val id: String,
    val question: String, // English text
    val questionSinhala: String, // Sinhala text
    val options: List<String>, // English options
    val optionsSinhala: List<String>, // Sinhala options
    val correctAnswerIndex: Int,
    val grade: Int,
    val subject: String,
    val stage: Int, // 1, 2, or 3
    val difficulty: String, // easy/medium/hard
    val hint: String,
    val hintSinhala: String
) {
    val text: String get() = question
    val questionId: String get() = id
}
