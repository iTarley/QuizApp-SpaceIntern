package com.space.quizapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.space.quizapp.data.local.model.entity.UserQuizPointsEntity

@Dao
interface UserQuizPointsDao {
    @Query("SELECT * FROM user_quiz_points WHERE userId = :userId")
    suspend fun getUserQuizPoints(userId: String): List<UserQuizPointsEntity>

    @Query("SELECT * FROM user_quiz_points WHERE userId = :userId AND subjectId = :subjectId")
    suspend fun getUserQuizPointsBySubject(userId: String, subjectId: Int): UserQuizPointsEntity?

    @Insert
    suspend fun insertUserQuizPoints(userQuizPoints: UserQuizPointsEntity)

    // Additional queries for updates, deletions, etc.
}