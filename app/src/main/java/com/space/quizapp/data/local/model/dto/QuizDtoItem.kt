package com.space.quizapp.data.local.model.dto


data class QuizDtoItem(
    val id: Int = 0,
    val image: String? = "",
    val questions: List<QuestionDto> = emptyList(),
    val questionsCount: Int? = 0,
    val quizDescription: String? = "",
    val quizTitle: String? = ""
) {
    data class QuestionDto(
        val answers: List<String>,
        val correctAnswer: String,
        val questionIndex: Int,
        val questionTitle: String
    )
}