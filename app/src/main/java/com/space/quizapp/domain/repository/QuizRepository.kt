package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.util.network.RequestResource


interface QuizRepository {
    suspend fun getQuiz(): RequestResource<List<QuizDomainModel>>
    suspend fun getQuizByUserId(userId:String): List<QuizPointsDomainModel>
}