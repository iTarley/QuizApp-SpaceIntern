package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserAuthRepository
import com.space.quizapp.utils.Resource

class AuthorizeUserUseCaseImpl(private val userAuthRepository: UserAuthRepository) : AuthorizeUserUseCase {

    override suspend fun authorizeUser(user: UserDomainModel):Resource {
        if (!isValidUsername(user.username)) {
            return Resource.Error("არავალიდური იუზერნეიმი")
        }

        val existingUser = userAuthRepository.getUserByUsername(user.username)
        if (existingUser != null) {
            return Resource.Success
        }

        userAuthRepository.saveUser(user)
        return Resource.Success
    }

    private fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9][a-zA-Z0-9_]{7,19}$")
        return username.matches(regex)
    }
}