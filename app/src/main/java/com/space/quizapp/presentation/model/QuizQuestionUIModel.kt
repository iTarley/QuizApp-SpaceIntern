package com.space.quizapp.presentation.model

data class QuizQuestionUIModel(
    val id: Int,
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val correctAnswer: String,
    val questionIndex: Int
){
    data class Answer(
        val answer:String,
    )
}