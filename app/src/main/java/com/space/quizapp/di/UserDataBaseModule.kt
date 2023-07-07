package com.space.quizapp.di

import android.content.Context
import androidx.room.Room
import com.space.data.UserDatabase
import org.koin.dsl.module

private fun provideUserDataBase(context: Context): com.space.data.UserDatabase {
    return Room.databaseBuilder(context, com.space.data.UserDatabase::class.java, "quiz_db").fallbackToDestructiveMigration().build()
}

private fun provideDao(db: com.space.data.UserDatabase) = db.userDao()

private fun providePointsDao(db: com.space.data.UserDatabase) = db.pointsDao()

private fun provideQuizDao(db: com.space.data.UserDatabase) = db.quizDao()

val userDataBaseModule = module {
    single { provideUserDataBase(get()) }
    single { provideDao(get()) }
    single { providePointsDao(get()) }
    single { provideQuizDao(get()) }
}