package com.space.quizapp.domain.usecase.points.get

import com.space.quizapp.domain.repository.UserAuthRepository

class GetUserPointsUseCaseImpl(private val userAuthRepository: UserAuthRepository) :
    GetUserPointsUseCase {

    override suspend fun getUserPoints(username: String): Double? {
        return userAuthRepository.getUserPoints(username)
    }
}