package com.space.quizapp.data.local.model.mapper.quiz

import com.space.quizapp.data.local.model.entity.QuizSubjectEntity
import com.space.quizapp.data.local.model.dto.QuizDtoItem
import com.space.quizapp.utils.mapper.ModelMapper

class QuizDtoEntityMapper : ModelMapper<QuizDtoItem, QuizSubjectEntity> {
    override fun invoke(model: QuizDtoItem): QuizSubjectEntity {
        return QuizSubjectEntity(
            id = model.id,
            quizTitle = model.quizTitle,
            quizDescription = model.quizDescription,
            image = model.image,
            questionsCount = model.questionsCount,
        )
    }
}

