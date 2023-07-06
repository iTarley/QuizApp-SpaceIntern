package com.space.quizapp.domain.usecase.quiz.get.quiz_gpa

import com.space.quizapp.domain.model.QuizDomainModel
import com.space.quizapp.domain.repository.QuizRepository

class GetQuizForGpaUseCaseImpl(private val quizRepository: QuizRepository):GetQuizForGpaUseCase {
    override suspend fun invoke(userId: String): List<QuizDomainModel> {
        return quizRepository.getQuizByUserId(userId)
    }
}
