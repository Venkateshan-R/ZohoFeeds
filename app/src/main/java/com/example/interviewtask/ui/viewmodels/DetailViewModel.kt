package com.example.interviewtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: FeedsRepository) : ViewModel() {

    var selectedId: String = ""
    var selectedPost = MutableStateFlow<Stream?>(null)


  /*  init {
        println("called$$ init $selectedId")
        selectedPost.value = repository.getPostById(selectedId)
    }*/

    fun getTheSelectedPost(){
        println("called$$ getTheSelectedPost $selectedId")
        println("called$$ returning object "+repository.getPostById(selectedId).toString())
        selectedPost.value = repository.getPostById(selectedId)
    }
}