package com.space.quizapp.domain.usecase.points

interface GetUserPointsUseCase {
    suspend fun getUserPoints(username: String): Double?
}