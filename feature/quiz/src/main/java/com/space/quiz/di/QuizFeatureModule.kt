package com.space.quiz.di

import com.space.quiz.data.QuizQuestionEntityDomainMapper
import com.space.quiz.data.QuizQuestionRepositoryImpl
import com.space.quiz.domain.repository.QuizQuestionRepository
import com.space.quiz.domain.usecase.GetQuizQuestionUseCase
import com.space.quiz.domain.usecase.GetQuizQuestionUseCaseImpl
import com.space.quiz.presentation.model.QuestionDomainUIMapper
import com.space.quiz.presentation.ui.QuizViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val quizFeatureModule = module {

    single<QuizQuestionRepository> {
        QuizQuestionRepositoryImpl(
            get(),
            QuizQuestionEntityDomainMapper(),
        )
    }
    single <GetQuizQuestionUseCase>{
        GetQuizQuestionUseCaseImpl(
            get()
        )
    }
    viewModel{
        QuizViewModel(
            get(),
            QuestionDomainUIMapper(),
        )
    }

}