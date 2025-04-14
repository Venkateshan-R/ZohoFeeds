package com.example.interviewtask.data.repository

import com.example.interviewtask.data.api.PostApiService
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.ui.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepository @Inject constructor(private val postApiService: PostApiService) {

    private var postModel: PostModel? = null

    init {
        println("called$$ Feeds repo object initializaiton")
    }

    fun getAllThePosts() = flow<UiState<PostModel>> {
        emit(UiState.Loading)
        postModel = postApiService.getPosts();
        postModel?.let {
            emit(UiState.Success(postModel!!))
        }
    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }

    fun getPostById(id: String) = flow<UiState<Stream>> {
        println("datadss getPostById $id "+postModel?.recentStreams?.streams?.toString())
        println("datas@ repo called")

        println("called$$ gtetpoasss")
        emit(UiState.Loading)
        postModel?.recentStreams?.streams?.first { it.id == id }?.let {
            emit(UiState.Success(it))
        } ?: emit(UiState.Failure("Post not found"))

    }.catch {
        emit(UiState.Failure(it.message.toString()))
    }

}