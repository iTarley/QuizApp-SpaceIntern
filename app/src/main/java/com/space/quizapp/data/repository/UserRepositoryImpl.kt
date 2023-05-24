package com.space.quizapp.data.repository

import com.space.quizapp.data.local.dao.UserDao
import com.space.quizapp.data.mapper.UserDomainEntityMapper
import com.space.quizapp.data.mapper.UserEntityDomainMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val domainEntityMapper: UserDomainEntityMapper,
    private val entityDomainMapper: UserEntityDomainMapper
) : UserRepository {

    override suspend fun getUserByUsername(username: String) =
        userDao.getUserByUsername(username)?.let { entityDomainMapper(it) }

    override suspend fun saveUser(user: UserDomainModel) {
        userDao.saveUser(domainEntityMapper(user))
    }

    override suspend fun getUserPoints(username: String): Double? {
        val userEntity = userDao.getUserByUsername(username)
        val userDomainModel = userEntity?.let { entityDomainMapper(it) }
        return userDomainModel?.points
    }
}