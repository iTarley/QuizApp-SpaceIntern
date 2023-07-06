package com.space.quizapp.presentation.ui.quiz

import androidx.lifecycle.MutableLiveData
import com.space.quizapp.domain.usecase.quiz.get.quiz_question.GetQuizQuestionUseCase
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.model.mapper.quiz.question.QuestionDomainUIMapper
import com.space.quizapp.presentation.ui.base.viewmodel.QuizBaseViewModel
import com.space.quizapp.utils.extensions.viewModelScope

class QuizViewModel(
    private val getQuizQuestionUseCase: GetQuizQuestionUseCase,
    private val mapper: QuestionDomainUIMapper
) : QuizBaseViewModel() {

    private val _quizData = MutableLiveData<List<QuizQuestionUIModel>>()
    val quizData get() = _quizData

    private val _quizAnswer = MutableLiveData<List<QuizQuestionUIModel.Answer>>()
    val quizAnswer get() = _quizAnswer

    private val _correctStatus = MutableLiveData<QuizQuestionUIModel.Answer>()
    val correctStatus get() = _correctStatus

    private val _wrongStatus = MutableLiveData<QuizQuestionUIModel.Answer>()
    val wrongStatus get() = _wrongStatus

    var itemId = 0

    fun fetchQuizData(id: Int) {
        viewModelScope {
            val quiz = getQuizQuestionUseCase.invoke(id).map {
                mapper(it)
            }
            _quizData.value = quiz
            _quizAnswer.value = quiz[itemId].data
        }
    }


    fun validateAnswer(answer: QuizQuestionUIModel.Answer, quiz:QuizQuestionUIModel) {
        if (answer.answer == quiz.correctAnswer) {
            _correctStatus.value = answer
        } else {
            _wrongStatus.value = answer
        }
    }
}