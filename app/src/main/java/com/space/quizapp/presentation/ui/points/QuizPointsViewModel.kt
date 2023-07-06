package com.space.quizapp.presentation.ui.points


import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope

class QuizPointsViewModel(
    private val clearUserSessionUseCase: ClearUserSessionUseCase
) : QuizBaseViewModel() {


    fun clearUserSession(){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            navigate(QuizPointsFragmentDirections.actionQuizPointsFragmentToQuizLogInFragment())
        }
    }
}