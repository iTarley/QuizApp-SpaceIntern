package com.space.quizapp.data.repository

import com.space.quizapp.data.local.dao.UserQuizPointsDao
import com.space.quizapp.data.local.model.entity.UserQuizPointsEntity
import com.space.quizapp.domain.repository.UserQuizPointsRepository

class UserQuizPointsRepositoryImpl(private val userQuizPointsDao: UserQuizPointsDao) :
    UserQuizPointsRepository {


    override suspend fun insertUserQuizPoints(userId: String, subjectId: Int, quizPoints: Int) {
        // Insert a new row for the subject
        val newUserQuizPoints = UserQuizPointsEntity(
            userId = userId,
            subjectId = subjectId,
            quizPoints = quizPoints
        )
        userQuizPointsDao.insertUserQuizPoints(newUserQuizPoints)
    }
}
