package com.space.quizapp.data.remote.service

import com.space.quizapp.data.remote.model.QuizDtoItem
import retrofit2.Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("cbbae797-340c-4a79-9b61-da3b45373e02")
    suspend fun retrieveQuestions(): Response<List<QuizDtoItem>>

    companion object {
        const val BASE_URL = "https://run.mocky.io/v3/"
    }
}