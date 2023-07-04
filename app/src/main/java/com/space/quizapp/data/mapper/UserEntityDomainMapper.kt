package com.space.quizapp.data.mapper

import com.space.quizapp.data.local.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserEntityDomainMapper : ModelMapper<UserEntity, UserDomainModel> {
    override operator fun invoke(model: UserEntity): UserDomainModel =
        with(model) {
            UserDomainModel(
                id = id,
                username = username,
                points = points
            )
        }
}