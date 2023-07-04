package com.space.quizapp.domain.usecase.points

import com.space.quizapp.domain.repository.UserRepository

class GetUserPointsUseCaseImpl(private val userRepository: UserRepository) : GetUserPointsUseCase {

    override suspend fun getUserPoints(username: String): Double? {
        return userRepository.getUserPoints(username)
    }
}