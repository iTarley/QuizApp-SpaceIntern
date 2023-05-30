package com.space.quizapp.presentation.ui.points


import androidx.lifecycle.ViewModel
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.utils.extensions.viewModelScope

class QuizPointsViewModel(
    private val clearUserSessionUseCase: ClearUserSessionUseCase
) : ViewModel() {

    fun clearUserSession(completion: () -> Unit){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            completion.invoke()
        }
    }
}