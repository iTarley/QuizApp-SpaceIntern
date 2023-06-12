package com.space.quizapp.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.space.quizapp.data.remote.model.dto.QuizDtoItem
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.java.KoinJavaComponent.get
import java.lang.reflect.Type

@Entity(tableName = "quiz_question")
data class QuizQuestionEntity(
    @PrimaryKey(autoGenerate = false)
    val questionTitle: String,
    val data: List<Answer> = emptyList(),
    val correctAnswer: String,
    val questionIndex: Int,
    val id: Int,
){
    data class Answer(
        val answer:String
    )
}



