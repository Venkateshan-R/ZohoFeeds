package com.example.interviewtask.data.repository

import android.content.Context
import android.util.Log
import com.example.interviewtask.data.local.dao.FeedsDao
import com.example.interviewtask.data.local.entities.StreamsEntity
import com.example.interviewtask.data.models.Comment
import com.example.interviewtask.data.remote.api.PostApiService
import com.example.interviewtask.data.models.FeedsModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.isNetworkAvailable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postApiService: PostApiService,
    private val feedsDao: FeedsDao
) : FeedsRepository {

    override suspend fun fetchAllTheFeeds() = withContext(Dispatchers.IO) {
        //checking network before API call
        if (isNetworkAvailable(context).not()) {
            throw Exception("No internet connection available")
        }
        return@withContext try {
            val feedsModel = postApiService.getPosts()
            feedsModel?.let { it ->
                insertAllTheStreams(it.recentStreams.streams.map { stream: Stream -> stream.toStreamsEntity() })
            }
            feedsModel ?: throw Exception("Something went wrong")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFeedById(id: String): Stream = withContext(Dispatchers.IO) {
        return@withContext getStreamByIdFromLocal(id).also {
//            Log.d("check",it.toString())
        } ?: throw Exception("Post not found")
    }

    private suspend fun insertAllTheStreams(streamsList: List<StreamsEntity>) {
        feedsDao.clearAllTheStream()
        feedsDao.insertAllTheStreams(streamsList)
    }

    //Fetch selected stream by id from Room database
    private suspend fun getStreamByIdFromLocal(id: String): Stream? =
        feedsDao.getStreamById(id)?.toStream()

}