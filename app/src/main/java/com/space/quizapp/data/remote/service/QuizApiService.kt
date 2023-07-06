package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("3c31a491-3b8f-485b-b89b-a0427a16ef02")
    suspend fun retrieveQuestions(): Response<List<QuizDtoItem>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}