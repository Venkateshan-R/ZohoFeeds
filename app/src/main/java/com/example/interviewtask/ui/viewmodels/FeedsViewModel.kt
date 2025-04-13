package com.example.interviewtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.ui.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedsViewModel @Inject constructor(val repository: FeedsRepository) : ViewModel() {

    var navigationEvent = MutableSharedFlow<String>()
    var postStateFlow: StateFlow<PostModel?> = repository.getAllThePosts()
        .stateIn(viewModelScope, started = SharingStarted.WhileSubscribed(5000), null)

    fun feedsItemClicked(stream: Stream) {
        viewModelScope.launch {
            navigationEvent.emit(Screens.Detail.withArgs(stream.id
            ))
        }
    }

}