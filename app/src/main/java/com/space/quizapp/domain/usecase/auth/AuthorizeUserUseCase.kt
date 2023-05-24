package com.space.quizapp.domain.usecase.auth

import com.space.quizapp.domain.model.UserDomainModel

//TODO ADD ABSTRACT CLASS FOR USE CASES
interface AuthorizeUserUseCase {
    suspend fun authorizeUser(user: UserDomainModel): Boolean
}