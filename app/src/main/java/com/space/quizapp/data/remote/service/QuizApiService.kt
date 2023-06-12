package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.dto.QuizDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("3db9fc84-d354-47de-977f-951178ddd14b\t")
    suspend fun retrieveQuestions(): Response<List<QuizDtoItem>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}