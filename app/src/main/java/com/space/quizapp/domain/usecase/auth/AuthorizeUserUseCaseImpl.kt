package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserAuthRepository

class AuthorizeUserUseCaseImpl(private val userAuthRepository: UserAuthRepository) : AuthorizeUserUseCase {

    override suspend fun authorizeUser(user: UserDomainModel): Boolean {
        if (!isValidUsername(user.username)) {
            return false
        }

        val existingUser = userAuthRepository.getUserByUsername(user.username)
        if (existingUser != null) {
            return true
        }

        userAuthRepository.saveUser(user)
        return true
    }

    private fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{7,19}$")
        return username.matches(regex)
    }
}