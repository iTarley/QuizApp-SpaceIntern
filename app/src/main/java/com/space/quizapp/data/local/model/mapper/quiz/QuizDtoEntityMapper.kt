package com.space.quizapp.data.local.model.mapper.quiz

import com.space.data.entity.QuizSubjectEntity
import com.space.quizapp.data.remote.model.QuizDtoItem
import com.space.util.mapper.ModelMapper

class QuizDtoEntityMapper : ModelMapper<QuizDtoItem, com.space.data.entity.QuizSubjectEntity> {
    override operator fun invoke(model: QuizDtoItem): com.space.data.entity.QuizSubjectEntity {
        return com.space.data.entity.QuizSubjectEntity(
            id = model.id,
            quizTitle = model.quizTitle,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            image = model.image,
        )
    }
}

