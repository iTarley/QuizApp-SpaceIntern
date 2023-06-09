package com.space.quizapp.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class QuizSubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String?,
    val questionsCount: Int?,
    val quizDescription: String?,
    val quizTitle: String?,
)
