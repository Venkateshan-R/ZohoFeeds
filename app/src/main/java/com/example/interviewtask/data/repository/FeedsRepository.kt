package com.example.interviewtask.data.repository

import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.UiState
import kotlinx.coroutines.flow.Flow

interface FeedsRepository {
    fun getPostById(id: String): Flow<UiState<Stream>>
   suspend fun getAllThePosts(): PostModel
}