package com.example.interviewtask.data.repository

import com.example.interviewtask.data.models.FeedsModel
import com.example.interviewtask.data.models.Stream

interface FeedsRepository {
    suspend fun getFeedById(id: String): Stream
    suspend fun fetchAllTheFeeds(): FeedsModel
}