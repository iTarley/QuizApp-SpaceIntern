package com.space.quizapp.di


import com.space.quizapp.presentation.ui.home.QuizHomeViewModel
import com.space.quizapp.presentation.ui.points.QuizPointsViewModel
import com.space.quizapp.presentation.ui.quiz.QuizViewModel
import com.space.quizapp.presentation.ui.log_in.LogInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        LogInViewModel()
    }
    viewModel{
        QuizHomeViewModel()
    }
    viewModel{
        QuizPointsViewModel()
    }
    viewModel{
        QuizViewModel()
    }
}