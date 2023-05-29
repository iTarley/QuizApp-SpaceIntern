package com.space.quizapp.di

import com.space.quizapp.data.mapper.UserDomainEntityMapper
import com.space.quizapp.data.mapper.UserEntityDomainMapper
import com.space.quizapp.data.repository.UserAuthRepositoryImpl
import com.space.quizapp.data.repository.UserSessionRepositoryImpl
import com.space.quizapp.domain.repository.UserAuthRepository
import com.space.quizapp.domain.repository.UserSessionRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserAuthRepository> { UserAuthRepositoryImpl(get(), UserDomainEntityMapper(),
        UserEntityDomainMapper()
    ) }
    single<UserSessionRepository> { UserSessionRepositoryImpl(get()) }
}