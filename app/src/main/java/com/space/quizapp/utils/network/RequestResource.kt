package com.space.quizapp.utils.network

data class RequestResource<out T>(
    val status: Status = Status.LOADING,
    val data: T? = null,
    val message: String? = ""
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): RequestResource<T> {
            return RequestResource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): RequestResource<T> {
            return RequestResource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): RequestResource<T> {
            return RequestResource(Status.LOADING, data, null)
        }
    }

}