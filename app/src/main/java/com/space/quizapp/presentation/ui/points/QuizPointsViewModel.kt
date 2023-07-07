package com.space.quizapp.presentation.ui.points


import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.current_user.clear.ClearUserSessionUseCase
import com.space.quizapp.domain.usecase.quiz.get.quiz_gpa.GetQuizForGpaUseCase
import com.space.quizapp.presentation.model.QuizPointsUIModel
import com.space.quizapp.presentation.model.mapper.quiz_point.QuizPointDomainUIMapper
import com.space.util.base.viewmodel.QuizBaseViewModel
import com.space.util.extensions.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizPointsViewModel(
    private val clearUserSessionUseCase: ClearUserSessionUseCase,
    private val getQuizForGpaUseCase: GetQuizForGpaUseCase,
    private val quizPointDomainUIMapper: QuizPointDomainUIMapper,
) : QuizBaseViewModel() {


    private val _quizData = MutableLiveData<List<QuizPointsUIModel>>()
    val quizData get() = _quizData


    fun clearUserSession(){
        viewModelScope {
            clearUserSessionUseCase.invoke()
            setNavigation(QuizPointsFragmentDirections.actionQuizPointsFragmentToQuizLogInFragment())
        }
    }

    fun getQuiz(userId: String) {
        viewModelScope {
            val quiz = withContext(Dispatchers.IO) {
                getQuizForGpaUseCase.invoke(userId).map {
                    quizPointDomainUIMapper(it)
                }
            }
            quizData.value = quiz
        }
    }
}