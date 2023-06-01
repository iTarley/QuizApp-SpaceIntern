package com.space.quizapp.di

import com.space.quizapp.data.local.model.mapper.quiz.QuizDtoDomainMapper
import com.space.quizapp.data.local.model.mapper.quiz.QuizQuestionDtoDomainMapper
import com.space.quizapp.data.local.entity.mapper.user.UserDomainEntityMapper
import com.space.quizapp.data.local.entity.mapper.user.UserEntityDomainMapper
import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.data.repository.QuizRepositoryImpl
import com.space.quizapp.data.repository.UserAuthRepositoryImpl
import com.space.quizapp.data.repository.UserSessionRepositoryImpl
import com.space.quizapp.domain.repository.QuizRepository
import com.space.quizapp.domain.repository.UserAuthRepository
import com.space.quizapp.domain.repository.UserSessionRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.dsl.module

val repositoryModule = module {
    single<UserAuthRepository> {
        UserAuthRepositoryImpl(
            get(), UserDomainEntityMapper(),
            UserEntityDomainMapper()
        )
    }
    single<UserSessionRepository> { UserSessionRepositoryImpl(get()) }
    single<QuizRepository> {
        QuizRepositoryImpl(
            get(), QuizDtoDomainMapper(
                QuizQuestionDtoDomainMapper()
            )
        )
    }
    single<QuizRepository> {
        QuizRepositoryImpl(
            get(), QuizDtoDomainMapper(
                QuizQuestionDtoDomainMapper()
            )
        )
    }
}