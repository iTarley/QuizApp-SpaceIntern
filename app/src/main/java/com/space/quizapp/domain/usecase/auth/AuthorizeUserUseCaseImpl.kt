package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserRepository

class AuthorizeUserUseCaseImpl(private val userRepository: UserRepository) : AuthorizeUserUseCase {

    override suspend fun authorizeUser(user: UserDomainModel): Boolean {
        if (!isValidUsername(user.username)) {
            return false
        }

        val existingUser = userRepository.getUserByUsername(user.username)
        if (existingUser != null) {
            return true
        }

        userRepository.saveUser(user)
        return true
    }

    private fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{7,19}$")
        return username.matches(regex)
    }
}