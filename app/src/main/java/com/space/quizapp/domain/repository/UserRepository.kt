package com.space.quizapp.domain.repository

import com.space.quizapp.domain.model.UserDomainModel

interface UserRepository {
    suspend fun getUserByUsername(username: String): UserDomainModel?
    suspend fun saveUser(user: UserDomainModel)
    suspend fun getUserPoints(username: String): Double?
}