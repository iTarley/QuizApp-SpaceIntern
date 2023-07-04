package com.space.quizapp

import android.app.Application
import com.space.quizapp.di.repositoryModule
import com.space.quizapp.di.useCaseModule
import com.space.quizapp.di.userDataBaseModule
import com.space.quizapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelModule,userDataBaseModule,repositoryModule,useCaseModule)
        }
    }
}