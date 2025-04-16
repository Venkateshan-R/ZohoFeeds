package com.example.interviewtask.data.repository

import com.example.interviewtask.data.api.PostApiService
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepositoryImpl @Inject constructor(private val postApiService: PostApiService) :
    FeedsRepository {

    private var postModel: PostModel? = null

    override fun getAllThePosts(): Flow<UiState<PostModel>> = flow {
        emit(UiState.Loading)
        postModel = postApiService.getPosts();
        postModel?.let {
            emit(UiState.Success(postModel!!))
        }
    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }

    override fun getPostById(id: String): Flow<UiState<Stream>> = flow {
        emit(UiState.Loading)
        postModel?.recentStreams?.streams?.first { it.id == id }?.let {
            emit(UiState.Success(it))
        } ?: emit(UiState.Failure("Post not found"))

    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }

}