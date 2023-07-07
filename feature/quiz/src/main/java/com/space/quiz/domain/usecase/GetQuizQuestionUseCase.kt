package com.space.quiz.domain.usecase

import com.space.quiz.domain.model.QuizQuestionDomainModel

interface GetQuizQuestionUseCase {
    suspend fun invoke(subjectId:Int): List<QuizQuestionDomainModel>
}