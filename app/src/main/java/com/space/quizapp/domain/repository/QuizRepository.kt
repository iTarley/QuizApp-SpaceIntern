package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.QuizDomainModelItem


interface QuizRepository {
    suspend fun getQuiz(): List<QuizDomainModelItem>
}