package com.space.quizapp.di

import android.content.Context
import androidx.room.Room
import com.space.quizapp.data.local.UserDatabase
import org.koin.dsl.module

private fun provideUserDataBase(context: Context): UserDatabase {
    return Room.databaseBuilder(context, UserDatabase::class.java, "user_database").fallbackToDestructiveMigration().build()
}

private fun provideDao(db: UserDatabase) = db.userDao()

val userDataBaseModule = module {
    single { provideUserDataBase(get()) }
    single { provideDao(get()) }
}