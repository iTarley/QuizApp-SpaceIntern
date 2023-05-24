package com.space.quizapp.di

import com.space.quizapp.data.mapper.UserDomainEntityMapper
import com.space.quizapp.data.mapper.UserEntityDomainMapper
import com.space.quizapp.data.repository.UserRepositoryImpl
import com.space.quizapp.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get(), UserDomainEntityMapper(),
        UserEntityDomainMapper()
    ) }
}