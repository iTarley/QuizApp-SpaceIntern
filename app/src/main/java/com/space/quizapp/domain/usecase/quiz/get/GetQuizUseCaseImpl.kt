package com.space.quizapp.domain.usecase.quiz.get

import com.space.quizapp.domain.model.QuizDomainModelItem
import com.space.quizapp.domain.repository.QuizRepository

class GetQuizUseCaseImpl(private val quizRepository: QuizRepository) : GetQuizUseCase {
    override suspend fun invoke(): List<QuizDomainModelItem> {
        return quizRepository.getQuiz()
    }
}