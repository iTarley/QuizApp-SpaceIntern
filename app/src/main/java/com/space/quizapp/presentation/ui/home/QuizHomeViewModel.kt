package com.space.quizapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCase
import com.space.quizapp.utils.extensions.viewModelScope

/**
 * QuizHomeViewModel is responsible for handling the home screen of the user
 * It displays the gpa of the user
 *
 */
class QuizHomeViewModel(
    private val getUserPointsUseCase: GetUserPointsUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase,
    private val clearUserSessionUseCase: ClearUserSessionUseCase
) : ViewModel() {

    private val _userPoints = MutableLiveData<Double?>()
    val userPoints: LiveData<Double?> = _userPoints

    private val _session = MutableLiveData<String>()
    val session: LiveData<String?> = _session

    private suspend fun getCurrentUserSession():Result<String> = getUserSessionUseCase.invoke()

    fun updateSession(){
        viewModelScope{
            getCurrentUserSession().getOrNull()?.let {
                _session.value = it
            }
        }
    }

    fun clearUserSession(completion: () -> Unit){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            completion.invoke()
        }
    }

    fun loadUserPoints(username: String) {
        viewModelScope {
            val points = getUserPointsUseCase.getUserPoints(username)
            _userPoints.value = points
        }
    }
}






