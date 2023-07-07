package com.space.quizapp.data.local.model.mapper.quiz

import com.space.data.entity.QuizSubjectEntity
import com.space.quizapp.domain.model.QuizDomainModel
import com.space.util.mapper.ModelMapper

class QuizEntityDomainMapper  : ModelMapper<com.space.data.entity.QuizSubjectEntity, QuizDomainModel> {
    override operator fun invoke(model: com.space.data.entity.QuizSubjectEntity): QuizDomainModel {
        return QuizDomainModel(
            id = model.id,
            quizTitle = model.quizTitle,
            questionsCount = model.questionsCount,
            quizDescription = model.quizDescription,
            image = model.image,
        )
    }
}