package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.model.QuizQuestionDomainModel
import com.space.quizapp.utils.network.RequestResource


interface QuizRepository {
    suspend fun getQuiz(): RequestResource<List<QuizDomainModel>>
    suspend fun getQuizQuestion(id:Int): List<QuizQuestionDomainModel>
}