package com.space.data.entity.type_converter

import androidx.room.TypeConverter
import com.space.data.entity.QuizQuestionEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.koin.java.KoinJavaComponent
import java.lang.reflect.Type

class ListAnswerConverter {
    private val moshi: Moshi = KoinJavaComponent.get(Moshi::class.java)

    private val listAnswerType: Type = com.squareup.moshi.Types.newParameterizedType(List::class.java, QuizQuestionEntity.Answer::class.java)
    private val adapter: JsonAdapter<List<QuizQuestionEntity.Answer>> = moshi.adapter(listAnswerType)

    @TypeConverter
    fun fromString(value: String): List<QuizQuestionEntity.Answer> {
        return adapter.fromJson(value) ?: emptyList()
    }

    @TypeConverter
    fun toString(list: List<QuizQuestionEntity.Answer>): String {
        return adapter.toJson(list)
    }
}