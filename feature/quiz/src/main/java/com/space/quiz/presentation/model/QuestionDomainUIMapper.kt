package com.space.quiz.presentation.model


import com.space.quiz.domain.model.QuizQuestionDomainModel
import com.space.util.mapper.ModelMapper

class QuestionDomainUIMapper:
    ModelMapper<QuizQuestionDomainModel, QuizQuestionUIModel> {
    override fun invoke(model: QuizQuestionDomainModel): QuizQuestionUIModel {

        val answersUI = model.data.map { answer ->
            QuizQuestionUIModel.Answer(
                answer = answer.answer,
                correctAnswer = model.correctAnswer,
                lastQuestion = answer.lastQuestion,
                point = model.point
            )
        }

        return QuizQuestionUIModel(
            subjectId = model.subjectId,
            questionTitle = model.questionTitle,
            data = answersUI,
            point = model.point,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex
        )
    }
}