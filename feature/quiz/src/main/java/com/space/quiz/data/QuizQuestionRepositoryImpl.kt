package com.space.quiz.data

import com.space.data.dao.QuizDao
import com.space.quiz.domain.model.QuizQuestionDomainModel
import com.space.quiz.domain.repository.QuizQuestionRepository
import com.space.util.network.RequestHandler

class QuizQuestionRepositoryImpl(
    private val quizDao: QuizDao,
    private val quizQuestionEntityDtoMapper: QuizQuestionEntityDomainMapper,
) : QuizQuestionRepository, RequestHandler() {


    override suspend fun getQuizQuestion(subjectId: Int): List<QuizQuestionDomainModel> {
        return quizDao.getQuizQuestion(subjectId).map {
            quizQuestionEntityDtoMapper(it)
        }
    }

}