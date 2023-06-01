package com.space.quizapp.domain.model

data class QuizDomainModelItem(
    val id: String?,
    val image: String?,
    val questions: List<Question>,
    val questionsCount: Int?,
    val quizDescription: String?,
    val quizTitle: String?
) {
    data class Question(
        val answers: List<String>,
        val correctAnswer: String?,
        val questionIndex: Int?,
        val questionTitle: String?
    )
}
