package com.space.quizapp.domain.model



data class QuizQuestionDomainModel(
    val id: Int = 0,
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val correctAnswer: String,
    val questionIndex: Int
){
    data class Answer(
        val answer:String
    )
}