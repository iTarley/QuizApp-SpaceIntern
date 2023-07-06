package com.space.quizapp.domain.usecase.quiz.get.quiz

import android.util.Log
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.repository.QuizRepository

class GetQuizUseCaseImpl(private val quizRepository: QuizRepository) : GetQuizUseCase {
    override suspend fun invoke(): List<QuizDomainModel> {
        return quizRepository.getQuiz()
    }
}