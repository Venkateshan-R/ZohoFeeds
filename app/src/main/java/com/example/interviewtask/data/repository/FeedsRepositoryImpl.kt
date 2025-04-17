package com.example.interviewtask.data.repository

import android.content.Context
import com.example.interviewtask.data.api.PostApiService
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.UiState
import com.example.interviewtask.ui.utils.isNetworkAvailable
import com.example.interviewtask.ui.utils.showToast
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context, private val postApiService: PostApiService
) : FeedsRepository {

    private var postModel: PostModel? = null
    override suspend fun getAllThePosts(): PostModel {
        if (isNetworkAvailable(context).not()) {
            throw Exception("No internet connection available")
        }
        return try {
            postModel = postApiService.getPosts()
            postModel ?: let {
                throw Exception("Something went wrong")
            }
        } catch (e: Exception) {
            throw e
        }
    }

    /*    override fun getAllThePosts(): Flow<UiState<PostModel>> = flow {
        if (isNetworkAvailable(context)) {
            emit(UiState.Loading)
            postModel = postApiService.getPosts();
            postModel?.let {
                emit(UiState.Success(postModel!!))
            }
        } else {
            throw Exception("No internet connection available")
        }
    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }*/

    override fun getPostById(id: String): Flow<UiState<Stream>> = flow {
        emit(UiState.Loading)
        postModel?.recentStreams?.streams?.first { it.id == id }?.let {
            emit(UiState.Success(it))
        } ?: emit(UiState.Failure("Post not found"))

    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }

}