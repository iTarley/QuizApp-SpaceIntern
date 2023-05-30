package com.space.quizapp.data.repository


import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.domain.model.QuizDomainModelItem
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.utils.quizJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException

class QuizRepositoryImpl(
    private val jsonAdapter: JsonAdapter<List<QuizDtoItem>>,
    private val mapper: QuizDtoDomainMapper
) : QuizRepository {
    override suspend fun getQuiz(): List<QuizDomainModelItem> {
        val jsonString = quizJson
        return jsonAdapter.fromJson(jsonString)?.map {
            mapper(it)
        } ?: throw JsonDataException("Failed to parse JSON")
    }
}