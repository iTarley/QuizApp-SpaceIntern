package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel

interface AuthorizeUserUseCase {
    suspend fun authorizeUser(user: UserDomainModel): Boolean
}