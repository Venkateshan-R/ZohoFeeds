package com.example.interviewtask.data.repository

import android.content.Context
import com.example.interviewtask.data.api.PostApiService
import com.example.interviewtask.data.models.FeedsModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context, private val postApiService: PostApiService
) : FeedsRepository {

    private var feedsModel: FeedsModel? = null

    override suspend fun fetchAllTheFeeds() = withContext(Dispatchers.IO) {
        if (isNetworkAvailable(context).not()) {
            throw Exception("No internet connection available")
        }
        return@withContext try {
            feedsModel = postApiService.getPosts()
            feedsModel ?: let {
                throw Exception("Something went wrong")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getFeedById(id: String): Stream = withContext(Dispatchers.IO) {
        return@withContext feedsModel?.recentStreams?.streams?.first { it.id == id }
            ?: throw Exception("Post not found")
    }

}