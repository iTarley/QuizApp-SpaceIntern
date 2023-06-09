package com.space.quizapp.data.repository


import com.space.quizapp.data.local.dao.QuizDao
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoEntityMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDtoMapper
import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDomainMapper
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.utils.quizJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException

class QuizRepositoryImpl(
    private val jsonAdapter: JsonAdapter<List<QuizDtoItem>>,
    private val quizDao: QuizDao,
    //mappers for quiz
    private val quizDtoDomainMapper: QuizDtoDomainMapper,
    private val quizDtoEntityMapper: QuizDtoEntityMapper,
    private val quizEntityDomainMapper: QuizEntityDomainMapper,
    //mappers for quiz questions
    private val quizQuestionEntityDomainMapper: QuizQuestionEntityDomainMapper,
    private val quizQuestionEntityDtoMapper: QuizQuestionEntityDtoMapper,
) : QuizRepository {
    override suspend fun getQuiz(): List<QuizDomainModel> {
        val jsonString = quizJson

        // Check if data already exists in Room
        val existingData = quizDao.getQuiz()
        if (existingData.isNotEmpty()) {
            // Return the existing data
            return existingData.map { quizEntityDomainMapper(it) }
        }

        // Parse the JSON and insert into Room
        jsonAdapter.fromJson(jsonString)?.let { dataList ->
            dataList.map {
                quizDao.insertQuiz(quizDtoEntityMapper(it))

            }
            dataList.map {
                quizDao.insertQuizQuestion(quizQuestionEntityDtoMapper(it))

            }
            return dataList.map { quizDtoDomainMapper(it) }
        }

        throw JsonDataException("JSON is null")
    }

    override suspend fun getQuizQuestion(id:Int): List<QuizQuestionDomainModel> {
        return quizDao.getQuizQuestion(id).map {
            quizQuestionEntityDomainMapper(it)
        }
    }
}