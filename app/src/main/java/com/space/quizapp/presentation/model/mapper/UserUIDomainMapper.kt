package com.space.quizapp.presentation.model.mapper

import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserUIDomainMapper : ModelMapper<UserUIModel, UserDomainModel> {
    override operator fun invoke(model: UserUIModel): UserDomainModel =
        UserDomainModel(
            id = model.id,
            username = model.username,
            points = model.points
        )
}