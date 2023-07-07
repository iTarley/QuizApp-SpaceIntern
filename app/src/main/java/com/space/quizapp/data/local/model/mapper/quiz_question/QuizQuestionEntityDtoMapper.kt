package com.space.quizapp.data.local.model.mapper.quiz_question

import com.space.quizapp.data.remote.model.QuizDtoItem
import com.space.data.entity.QuizQuestionEntity
import com.space.util.mapper.ModelMapper

class QuizQuestionEntityDtoMapper : ModelMapper<QuizDtoItem, List<com.space.data.entity.QuizQuestionEntity>> {
    override operator fun invoke(model: QuizDtoItem): List<com.space.data.entity.QuizQuestionEntity> {
        return model.questions.map { questionDto ->
            com.space.data.entity.QuizQuestionEntity(
                questionTitle = questionDto.questionTitle,
                data = questionDto.answers.map {
                    com.space.data.entity.QuizQuestionEntity.Answer(
                        answer = it,
                        lastQuestion = questionDto.lastQuestion
                    )
                },
                point = questionDto.point,
                correctAnswer = questionDto.correctAnswer,
                questionIndex = questionDto.questionIndex,
                subjectId = model.id
            )
        }
    }
}