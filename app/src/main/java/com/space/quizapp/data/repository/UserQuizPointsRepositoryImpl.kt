package com.space.quizapp.data.repository

import com.space.data.dao.UserQuizPointsDao
import com.space.data.entity.UserQuizPointsEntity
import com.space.quizapp.domain.repository.UserQuizPointsRepository

class UserQuizPointsRepositoryImpl(private val userQuizPointsDao: com.space.data.dao.UserQuizPointsDao) :
    UserQuizPointsRepository {

    override suspend fun insertUserQuizPoints(userId: String, subjectId: Int, quizPoints: Double) {
        val existingUserQuizPoints = userQuizPointsDao.getUserQuizPoints(userId, subjectId)

        if (existingUserQuizPoints == null) {
            // Insert a new row as there is no existing record
            val newUserQuizPoints = com.space.data.entity.UserQuizPointsEntity(
                userId = userId,
                subjectId = subjectId,
                quizPoints = quizPoints
            )
            userQuizPointsDao.insertUserQuizPoints(newUserQuizPoints)
        } else {
            // Update the existing row if the new quiz points are greater
            if (quizPoints > existingUserQuizPoints.quizPoints) {
                existingUserQuizPoints.quizPoints = quizPoints
                userQuizPointsDao.updateUserQuizPoints(existingUserQuizPoints)
            }
        }
    }
}

