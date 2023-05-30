package com.space.quizapp.presentation.ui.points

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewModelScope

class QuizPointsViewModel(
    private val clearUserSessionUseCase: ClearUserSessionUseCase
) : ViewModel() {

    fun clearUserSession(navController: NavController){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            navigateSafe(navController, QuizPointsFragmentDirections.actionQuizPointsFragmentToQuizLogInFragment())
        }
    }
}