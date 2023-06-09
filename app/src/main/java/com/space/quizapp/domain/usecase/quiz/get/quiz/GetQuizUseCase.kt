package com.space.quizapp.domain.usecase.quiz.get.quiz

import com.space.quizapp.domain.model.QuizDomainModel


interface GetQuizUseCase {
    suspend fun invoke(): List<QuizDomainModel>
}
