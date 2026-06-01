package com.example.data

object QuestionBank {

    // Main entry point: retrieves exactly 10 questions for a given grade, subject, and stage
    fun getQuestions(grade: Int, subject: String, stage: Int): List<Question> {
        val normalizedSub = when (subject.lowercase().trim()) {
            "math", "mathematics", "ගණිතය" -> "Math"
            "science", "විද්‍යාව", "විද්යාව" -> "Science"
            "english", "ඉංග්‍රීසි", "ඉංග්රීසි" -> "English"
            "history", "ඉතිහාසය" -> "History"
            else -> "Math"
        }

        return when (normalizedSub) {
            "Math" -> {
                if (grade <= 5) {
                    MathQuestions.getQuestions(grade, stage)
                } else {
                    MathQuestions2.getQuestions(grade, stage)
                }
            }
            "Science" -> {
                if (grade <= 5) {
                    ScienceQuestions.getQuestions(grade, stage)
                } else {
                    ScienceQuestions2.getQuestions(grade, stage)
                }
            }
            "English" -> {
                if (grade <= 5) {
                    EnglishQuestions.getQuestions(grade, stage)
                } else {
                    EnglishQuestions2.getQuestions(grade, stage)
                }
            }
            "History" -> {
                if (grade <= 5) {
                    HistoryQuestions.getQuestions(grade, stage)
                } else {
                    HistoryQuestions2.getQuestions(grade, stage)
                }
            }
            else -> emptyList()
        }
    }
}
