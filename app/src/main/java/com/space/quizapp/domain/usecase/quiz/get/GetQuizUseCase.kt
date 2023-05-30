package com.space.quizapp.domain.usecase.quiz.get

import com.space.quizapp.domain.model.QuizDomainModelItem


interface GetQuizUseCase {
    suspend fun invoke(): List<QuizDomainModelItem>
}
