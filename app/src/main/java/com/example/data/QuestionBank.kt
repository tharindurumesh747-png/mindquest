package com.example.data

object QuestionBank {
    private val questions = mutableListOf<Question>()

    init {
        questions.addAll(MathQuestions.list)
        questions.addAll(MathQuestions2.list)
        questions.addAll(ScienceQuestions.list)
        questions.addAll(ScienceQuestions2.list)
        questions.addAll(EnglishQuestions.list)
        questions.addAll(EnglishQuestions2.list)
        questions.addAll(HistoryQuestions.list)
        questions.addAll(HistoryQuestions2.list)
    }

    fun getQuestionsFor(grade: Int, subject: String, stage: Int): List<Question> {
        val filtered = questions.filter { 
            it.grade == grade && 
            it.subject.equals(subject, ignoreCase = true) && 
            it.stage == stage 
        }
        if (filtered.isNotEmpty()) return filtered

        // Fallback: search by grade and subject (ignore stage)
        val fallback = questions.filter { 
            it.grade == grade && 
            it.subject.equals(subject, ignoreCase = true) 
        }
        if (fallback.isNotEmpty()) return fallback

        // Extreme fallback: return first 3 questions of that subject
        val extreme = questions.filter { it.subject.equals(subject, ignoreCase = true) }
        if (extreme.isNotEmpty()) return extreme

        // Absolute fallback: all questions
        return questions
    }
}
