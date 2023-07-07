package com.space.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.space.data.dao.QuizDao
import com.space.data.dao.UserDao
import com.space.data.dao.UserQuizPointsDao
import com.space.data.entity.QuizQuestionEntity
import com.space.data.entity.QuizSubjectEntity
import com.space.data.entity.UserEntity
import com.space.data.entity.UserQuizPointsEntity
import com.space.data.entity.type_converter.ListAnswerConverter


@Database(
    entities = [UserEntity::class, QuizQuestionEntity::class, QuizSubjectEntity::class, UserQuizPointsEntity::class],
    version = 16,
    exportSchema = false
)
@TypeConverters(ListAnswerConverter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun quizDao(): QuizDao
    abstract fun pointsDao(): UserQuizPointsDao
}