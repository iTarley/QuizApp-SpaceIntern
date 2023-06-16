package com.space.quizapp.domain.usecase.points.insert

import com.space.quizapp.domain.repository.UserQuizPointsRepository

class InsertQuizPointsUseCaseImpl(private val userQuizPointsRepository: UserQuizPointsRepository):InsertQuizPointsUseCase {
    override suspend fun invoke(userId: String, subjectId: Int, quizPoints: Int) {
        userQuizPointsRepository.insertUserQuizPoints(userId, subjectId, quizPoints)
    }
}
