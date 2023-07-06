package com.space.quizapp.presentation.ui.base.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.viewModelScope

abstract class QuizBaseViewModel:ViewModel() {

    private val _errorStatus = MutableLiveData<Int>()
    val errorStatus get() = _errorStatus

    //TEMP VARIABLE
    private val _loadStatus = MutableLiveData<Int>()
    val loadStatus get() = _loadStatus

    private val _navigationState = MutableLiveData<NavDirections?>()
    val navigationState get() = _navigationState

    fun setErrorValue(message: Int) {
        _errorStatus.value = message
    }

    //TEMP VARIABLE
    fun setLoadValue(message: Int) {
        _loadStatus.value = message
    }

    private fun clearNavigationState() {
        _navigationState.value = null // Set the LiveData value to null or an initial state
    }

    fun navigate(direction: NavDirections) {
        _navigationState.value = direction
        clearNavigationState()
    }

}