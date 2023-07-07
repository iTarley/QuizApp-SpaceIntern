package com.space.quiz.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.space.quiz.domain.usecase.GetQuizQuestionUseCase
import com.space.quiz.presentation.model.QuestionDomainUIMapper
import com.space.quiz.presentation.model.QuizQuestionUIModel
import com.space.util.base.viewmodel.QuizBaseViewModel
import com.space.util.extensions.viewModelScope

class QuizViewModel(
    private val getQuizQuestionUseCase: GetQuizQuestionUseCase,
    private val mapper: QuestionDomainUIMapper,

) : QuizBaseViewModel() {

    private val _quizData = MutableLiveData<List<QuizQuestionUIModel>>()
    val quizData get() = _quizData

    var points = 0
    var quizId = 0
    var lastQuestion = false

    fun fetchQuizData(id: Int) {
        viewModelScope {
            val quiz = getQuizQuestionUseCase.invoke(id).map {
                mapper(it)
            }
            _quizData.value = quiz
        }
    }
    fun markAsLastQuestion(){
        lastQuestion = true
    }
    fun incrementQuizId(){
        quizId++
    }

    fun updatePoints(isCorrectAnswer:Boolean,points:Int){
        if(isCorrectAnswer){
            this.points += points
        }
    }

//    fun saveGpa(subjectId:Int,quizPoints:Double){
//        viewModelScope {
//            val userId = getUserSessionUseCase.invoke().getOrNull()
//            insertQuizPointsUseCase.invoke(userId = userId!!,subjectId = subjectId,quizPoints = quizPoints)
//        }
//    }
}