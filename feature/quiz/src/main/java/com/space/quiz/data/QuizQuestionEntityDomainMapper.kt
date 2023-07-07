package com.space.quiz.data

import com.space.data.entity.QuizQuestionEntity
import com.space.quiz.domain.model.QuizQuestionDomainModel
import com.space.util.mapper.ModelMapper

class QuizQuestionEntityDomainMapper :
    ModelMapper<QuizQuestionEntity, QuizQuestionDomainModel> {
    override operator fun invoke(model: QuizQuestionEntity): QuizQuestionDomainModel {

        val answersUI = model.data.map { answer ->
            QuizQuestionDomainModel.Answer(
                answer = answer.answer,
                lastQuestion = answer.lastQuestion
            )
        }

        return QuizQuestionDomainModel(
            questionTitle = model.questionTitle,
            data = answersUI,
            point = model.point,
            correctAnswer = model.correctAnswer,
            questionIndex = model.questionIndex,
            subjectId = model.subjectId
        )
    }
}
