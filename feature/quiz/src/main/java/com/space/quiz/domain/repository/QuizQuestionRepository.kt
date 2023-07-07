package com.space.quiz.domain.repository

import com.space.quiz.domain.model.QuizQuestionDomainModel

interface QuizQuestionRepository {
    suspend fun getQuizQuestion(subjectId:Int): List<QuizQuestionDomainModel>

}