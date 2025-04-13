package com.example.interviewtask.data.repository

import com.example.interviewtask.data.api.PostApiService
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedsRepository @Inject constructor(private val postApiService: PostApiService){

   private var postModel :PostModel? = null

    init {
        println("called$$ Feeds repo object initializaiton")
    }


    fun getAllThePosts() = flow<PostModel> {
        postModel =  postApiService.getPosts();
        postModel?.let {
            emit(it)
        }
    }

    fun getPostById(id:String): Stream? = postModel?.recentStreams?.streams?.filter {
        println("called$$ inner "+it.id)
        it.id == id  }.also {
        println("called$$ getpostbuid "+it.toString())
    }?.get(0)


}