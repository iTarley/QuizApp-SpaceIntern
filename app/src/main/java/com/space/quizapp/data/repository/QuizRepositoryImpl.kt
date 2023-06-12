package com.space.quizapp.data.repository


import com.space.quizapp.data.local.dao.QuizDao
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoEntityMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizEntityDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDtoMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz_question.QuizQuestionEntityDomainMapper
import com.space.quizapp.data.remote.service.QuizApiService
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.utils.network.RequestHandler
import com.space.quizapp.utils.network.RequestResource


class QuizRepositoryImpl(
    private val quizDao: QuizDao,
    //mappers for quiz
    private val quizDtoDomainMapper: QuizDtoDomainMapper,
    private val quizDtoEntityMapper: QuizDtoEntityMapper,
    private val quizEntityDomainMapper: QuizEntityDomainMapper,
    //mappers for quiz questions
    private val quizQuestionEntityDomainMapper: QuizQuestionEntityDomainMapper,
    private val quizQuestionEntityDtoMapper: QuizQuestionEntityDtoMapper,
    private val apiService: QuizApiService
) : QuizRepository, RequestHandler() {
    
    //TODO ADD FLOW
    override suspend fun getQuiz(): RequestResource<List<QuizDomainModel>> {
        val existingData = quizDao.getQuiz()
        if (existingData.isNotEmpty()) {
            // Return the existing data
            return RequestResource.success(existingData.map { quizEntityDomainMapper(it) })
        }

        val data = apiCall { apiService.retrieveQuestions() }
        if (data.status == RequestResource.Status.SUCCESS) {
            data.data?.forEach {
                quizDao.insertQuiz(quizDtoEntityMapper(it))
                quizDao.insertQuizQuestion(quizQuestionEntityDtoMapper(it))
            }
            return RequestResource.success(data.data?.map { quizDtoDomainMapper(it) }
                ?: emptyList())
        } else if (data.status == RequestResource.Status.ERROR) {
            return RequestResource.error(data.message ?: "Unknown error occurred", null)
        }

        return RequestResource.error("Failed to fetch data", null)
    }

    override suspend fun getQuizQuestion(id: Int): List<QuizQuestionDomainModel> {
        return quizDao.getQuizQuestion(id).map {
            quizQuestionEntityDomainMapper(it)
        }
    }
}