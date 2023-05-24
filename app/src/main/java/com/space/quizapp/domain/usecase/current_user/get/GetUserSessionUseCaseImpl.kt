package com.space.quizapp.domain.usecase.current_user.get

import com.space.quizapp.domain.repository.UserRepository

class GetUserSessionUseCaseImpl(private val userRepository: UserRepository):GetUserSessionUseCase{
    override suspend fun invoke(): Result<String> {
        return userRepository.getUserSession()
    }
}