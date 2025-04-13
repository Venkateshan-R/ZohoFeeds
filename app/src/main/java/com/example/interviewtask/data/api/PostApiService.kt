package com.example.interviewtask.data.api

import com.example.interviewtask.data.models.PostModel
import retrofit2.http.GET

interface PostApiService {
    @GET("b/3HDI")
    suspend fun getPosts(): PostModel
}
