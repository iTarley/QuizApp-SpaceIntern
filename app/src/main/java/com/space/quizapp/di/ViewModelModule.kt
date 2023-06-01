package com.space.quizapp.di


import com.space.quizapp.presentation.model.mapper.QuizDomainUIMapper
import com.space.quizapp.presentation.model.mapper.QuizQuestionDomainUIMapper
import com.space.quizapp.presentation.model.mapper.UserUIDomainMapper
import com.space.quizapp.presentation.ui.home.QuizHomeViewModel
import com.space.quizapp.presentation.ui.points.QuizPointsViewModel
import com.space.quizapp.presentation.ui.quiz.QuizViewModel
import com.space.quizapp.presentation.ui.log_in.QuizLogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        QuizLogInViewModel(get(),UserUIDomainMapper(),get(),get())
    }
    viewModel{
        QuizHomeViewModel(get(),get(),get(),get(), QuizDomainUIMapper(QuizQuestionDomainUIMapper()))
    }
    viewModel{
        QuizPointsViewModel(get())
    }
    viewModel{
        QuizViewModel(get(), QuizDomainUIMapper(QuizQuestionDomainUIMapper()))
    }
}