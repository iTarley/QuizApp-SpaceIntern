package com.space.quiz.domain.usecase

import com.space.quiz.domain.model.QuizQuestionDomainModel
import com.space.quiz.domain.repository.QuizQuestionRepository

class GetQuizQuestionUseCaseImpl(private val repository: QuizQuestionRepository): GetQuizQuestionUseCase {
    override suspend fun invoke(subjectId:Int): List<QuizQuestionDomainModel> {
        return repository.getQuizQuestion(subjectId)
    }
}