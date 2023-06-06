package com.space.quizapp.presentation.ui.quiz

import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.quiz.get.GetQuizUseCase
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.model.mapper.QuizDomainUIMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope

class QuizViewModel(
    private val getQuizUseCase: GetQuizUseCase,
    private val domainToUiMapper: QuizDomainUIMapper
) : QuizBaseViewModel() {

    private val _quizData = MutableLiveData<List<QuizUIModel>>()
    val quizData get() = _quizData

    fun fetchQuizData() {
        viewModelScope {
            val quiz = getQuizUseCase.invoke().map {
                domainToUiMapper(it)
            }
            _quizData.value = quiz
        }
    }
}