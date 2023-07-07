package com.space.quizapp.data.local.model.mapper.quiz_point

import com.space.data.entity.QuizPointsEntity
import com.space.quizapp.domain.model.QuizPointsDomainModel
import com.space.util.mapper.ModelMapper

class QuizPointEntityDomainMapper: ModelMapper<com.space.data.entity.QuizPointsEntity, QuizPointsDomainModel> {
    override operator fun invoke(model: com.space.data.entity.QuizPointsEntity): QuizPointsDomainModel {
        return QuizPointsDomainModel(
            id = model.id,
            image = model.image,
            quizDescription = model.quizDescription,
            quizTitle = model.quizTitle,
            quizPoints = model.quizPoints
        )
    }
}