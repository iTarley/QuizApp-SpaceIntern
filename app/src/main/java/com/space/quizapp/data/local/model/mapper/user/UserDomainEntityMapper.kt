package com.space.quizapp.data.local.model.mapper.user


import com.space.data.entity.UserEntity
import com.space.quizapp.domain.model.UserDomainModel
import com.space.util.mapper.ModelMapper

class UserDomainEntityMapper() : ModelMapper<UserDomainModel, com.space.data.entity.UserEntity> {
    override operator fun invoke(model: UserDomainModel): com.space.data.entity.UserEntity =
        with(model){
            com.space.data.entity.UserEntity(
                id = id,
                username = username,
            )
        }

}