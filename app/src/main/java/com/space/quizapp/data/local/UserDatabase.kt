package com.space.quizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.space.quizapp.data.local.dao.QuizDao
import com.space.quizapp.data.local.dao.UserDao
import com.space.quizapp.data.local.model.entity.QuizQuestionEntity
import com.space.quizapp.data.local.model.entity.QuizSubjectEntity
import com.space.quizapp.data.local.model.entity.UserEntity
import com.space.quizapp.data.local.model.entity.type_converter.ListAnswerConverter

@Database(
    entities = [UserEntity::class, QuizQuestionEntity::class, QuizSubjectEntity::class],
    version = 20,
    exportSchema = false
)
@TypeConverters(ListAnswerConverter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun quizDao(): QuizDao
}