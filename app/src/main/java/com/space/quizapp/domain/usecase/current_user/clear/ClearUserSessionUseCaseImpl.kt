package com.space.quizapp.domain.usecase.current_user.clear

import com.space.quizapp.domain.repository.UserRepository

class ClearUserSessionUseCaseImpl(private val userRepository: UserRepository): ClearUserSessionUseCase {

    override suspend fun invoke() {
        userRepository.clearUserSession()
    }
}