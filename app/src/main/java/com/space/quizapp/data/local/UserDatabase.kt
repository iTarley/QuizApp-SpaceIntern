package com.space.quizapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.space.quizapp.data.local.dao.UserDao
import com.space.quizapp.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}