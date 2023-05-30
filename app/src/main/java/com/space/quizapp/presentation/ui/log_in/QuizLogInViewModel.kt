package com.space.quizapp.presentation.ui.log_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.space.quizapp.domain.usecase.auth.AuthorizeUserUseCase
import com.space.quizapp.domain.usecase.current_user.get.GetUserSessionUseCase
import com.space.quizapp.domain.usecase.current_user.save.SaveUserSessionUseCase
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.model.mapper.UserUIDomainMapper
import com.space.quizapp.utils.Resource
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewModelScope

/**
 * QuizLogInViewModel is responsible for handling the log in and registration of the user
 */
class QuizLogInViewModel(
    private val authorizeUserUseCase: AuthorizeUserUseCase,
    private val userUIDomainMapper: UserUIDomainMapper,
    private val saveUserSessionUseCase: SaveUserSessionUseCase,
    private val getUserSessionUseCase: GetUserSessionUseCase
    ) : ViewModel() {

    private val _errorStatus = MutableLiveData<Int>()
    val errorStatus: LiveData<Int> get() = _errorStatus

    private val _session = MutableLiveData<String>()
    val session: LiveData<String?> get() = _session

    private suspend fun getCurrentUserSession():Result<String> = getUserSessionUseCase.invoke()

    /**
     * observeSession is responsible for observing the current user session
     */
    fun observeSession(){
        viewModelScope{
            getCurrentUserSession().getOrNull()?.let {
                _session.value = it
            }
        }
    }

    /**
     * authorizeUser is responsible for authorizing the user and navigating to home fragment
     */
    fun authorizeUser(user: UserUIModel,navController: NavController){
        viewModelScope{
            when (val status = authorizeUserUseCase.authorizeUser(userUIDomainMapper(user))) {
                is Resource.Success -> {
                    saveUserSessionUseCase.saveUserSession(user.username)
                    navigateToHome(navController)
                }
                is Resource.Error -> {
                    _errorStatus.value = status.message
                }
            }
        }
    }
    fun navigateToHome(navController: NavController){
        navigateSafe(navController,QuizLogInFragmentDirections.actionQuizLogInFragmentToQuizHomeFragment())
    }
}
