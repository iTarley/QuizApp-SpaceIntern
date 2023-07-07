package com.space.util

object UsernameValidator {
    fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9\u10D0-\u10FF][a-zA-Z0-9_\u10D0-\u10FF]{3,19}$")
        return username.matches(regex)
    }
}