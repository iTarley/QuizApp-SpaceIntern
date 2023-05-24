package com.space.quizapp.data.repository

import com.space.quizapp.data.local.dao.UserDao
import com.space.quizapp.data.local.data_store.SessionDataStoreManager
import com.space.quizapp.data.mapper.UserDomainEntityMapper
import com.space.quizapp.data.mapper.UserEntityDomainMapper
import com.space.quizapp.domain.model.UserDomainModel
import com.space.quizapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class UserRepositoryImpl(
    private val userDao: UserDao,
    private val dataStoreManager: SessionDataStoreManager,
    private val domainEntityMapper: UserDomainEntityMapper,
    private val entityDomainMapper: UserEntityDomainMapper
) : UserRepository {
    /**
     * This functions is used to handle the data store operations
     */
    override suspend fun getUserSession(): Result<String> {
        return Result.runCatching {
            val flow = dataStoreManager.getUsernameValue(STRING_KEY)
            flow.firstOrNull() ?: ""
        }
    }

    override suspend fun saveUserSession(user: String) {
        Result.runCatching {
            dataStoreManager.saveUsernameValue(STRING_KEY, user)
        }

    }

    override suspend fun clearUserSession() {
        Result.runCatching {
            dataStoreManager.clearUsernameValue(STRING_KEY)
        }
    }

    /**
     * This functions is used to handle room database operations
     */
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

    companion object {
        private const val STRING_KEY = "string_key"
    }
}