package com.space.quizapp.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_question")
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = false)
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val correctAnswer: String,
    val questionIndex: Int,
    val subjectId: Int,
){
    data class Answer(
        val answer:String
    )
}



