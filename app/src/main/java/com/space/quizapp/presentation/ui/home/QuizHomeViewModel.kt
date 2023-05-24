package com.space.quizapp.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.space.quizapp.domain.usecase.points.GetUserPointsUseCase
import com.space.quizapp.utils.extensions.viewModelScope

/**
 * QuizHomeViewModel is responsible for handling the home screen of the user
 * It displays the gpa of the user
 *
 */
class QuizHomeViewModel(
    private val getUserPointsUseCase: GetUserPointsUseCase,
) : ViewModel() {

    private val _userPoints = MutableLiveData<Double?>()
    val userPoints: LiveData<Double?> = _userPoints

    fun loadUserPoints(username: String) {
        viewModelScope {
            val points = getUserPointsUseCase.getUserPoints(username)
            _userPoints.value = points
        }
    }
}






