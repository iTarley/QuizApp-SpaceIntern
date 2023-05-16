package com.space.quizapp.di


import com.space.quizapp.presentation.ui.home.HomeViewModel
import com.space.quizapp.presentation.ui.points.ShowPointViewModel
import com.space.quizapp.presentation.ui.quiz.QuizViewModel
import com.space.quizapp.presentation.ui.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        StartViewModel()
    }
    viewModel{
        HomeViewModel()
    }
    viewModel{
        ShowPointViewModel()
    }
    viewModel{
        QuizViewModel()
    }
}