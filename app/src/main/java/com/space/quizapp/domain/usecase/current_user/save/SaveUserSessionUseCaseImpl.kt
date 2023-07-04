package com.space.quizapp.domain.usecase.current_user.save

import com.space.quizapp.domain.repository.UserRepository

class SaveUserSessionUseCaseImpl(private val userRepository: UserRepository) :
    SaveUserSessionUseCase {
    override suspend fun saveUserSession(username: String) {
        userRepository.saveUserSession(username)
    }
}