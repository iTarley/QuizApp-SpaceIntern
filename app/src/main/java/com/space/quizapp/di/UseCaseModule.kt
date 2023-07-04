package com.space.quizapp.di

import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCase
import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCaseImpl
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCase
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<AuthorizeUserUseCase> { AuthorizeUserUseCaseImpl(get()) }
    single<GetUserPointsUseCase> { GetUserPointsUseCaseImpl(get()) }
}