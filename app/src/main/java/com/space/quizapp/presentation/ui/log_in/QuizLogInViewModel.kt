package com.space.quizapp.presentation.ui.log_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCase
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.model.mapper.UserUIDomainMapper
import com.space.quizapp.utils.Resource
import com.space.quizapp.utils.extensions.viewModelScope
import kotlinx.coroutines.launch

/**
 * QuizLogInViewModel is responsible for handling the log in and registration of the user
 */
class QuizLogInViewModel(
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val userUIDomainMapper: UserUIDomainMapper
) : ViewModel() {

    private val _registrationStatus = MutableLiveData<Resource>()
    val registrationStatus: LiveData<Resource> = _registrationStatus

    fun authorizeUser(user: UserUIModel) {
        viewModelScope {
            val isSuccess = authorizeUserUseCase.authorizeUser(userUIDomainMapper(user))
            _registrationStatus.value = if (isSuccess) {
                Resource.Success
            } else {
                Resource.Error("Invalid username")
            }
        }
    }
}
