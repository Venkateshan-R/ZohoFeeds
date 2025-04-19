package com.example.interviewtask.data.remote.api

import com.example.interviewtask.data.models.FeedsModel
import retrofit2.http.GET

interface PostApiService {
    @GET("b/3HDI")
    suspend fun getPosts(): FeedsModel?
}
