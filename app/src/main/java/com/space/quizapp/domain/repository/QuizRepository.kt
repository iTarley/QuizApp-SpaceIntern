package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.model.QuizQuestionDomainModel


interface QuizRepository {
    suspend fun getQuiz(): List<QuizDomainModel>
    suspend fun getQuizQuestion(id:Int): List<QuizQuestionDomainModel>
}