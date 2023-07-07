package com.space.quizapp.data.local.model.mapper.user

import com.space.data.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.util.mapper.ModelMapper

class UserEntityDomainMapper : ModelMapper<com.space.data.entity.UserEntity, UserDomainModel> {
    override operator fun invoke(model: com.space.data.entity.UserEntity): UserDomainModel =
        with(model) {
            UserDomainModel(
                id = id,
                username = username,
            )
        }
}