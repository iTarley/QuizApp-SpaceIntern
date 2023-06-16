package com.space.quizapp.domain.repository

import com.space.quizapp.data.local.model.entity.UserQuizPointsEntity

interface UserQuizPointsRepository {
    suspend fun insertUserQuizPoints(userId: String, subjectId: Int, quizPoints: Int)
}