package com.space.quizapp.data.mapper


import com.space.quizapp.data.local.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.utils.mapper.ModelMapper

class UserDomainEntityMapper : ModelMapper<UserDomainModel, UserEntity> {
    override operator fun invoke(model: UserDomainModel): UserEntity =
        UserEntity(
            id = model.id,
            username = model.username,
            points = model.points
        )
}