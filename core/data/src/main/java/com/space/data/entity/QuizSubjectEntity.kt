package com.space.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class QuizSubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val questionsCount: Int,
    val image: String?,
    val quizDescription: String?,
    val quizTitle: String?,
)
