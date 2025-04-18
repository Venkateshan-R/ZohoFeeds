package com.example.interviewtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtask.data.models.PostModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.ui.navigation.Screens
import com.example.interviewtask.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedsViewModel @Inject constructor(val repository: FeedsRepository) : ViewModel() {

    val isRefreshing = MutableStateFlow<Boolean>(false)

    var navigationEvent = MutableSharedFlow<String>()

    private var _feedsStateFlow: MutableStateFlow<UiState<PostModel>> =
        MutableStateFlow(UiState.Loading)

    var feedsStateFlow = _feedsStateFlow as StateFlow<UiState<PostModel>>

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _feedsStateFlow.value = UiState.Failure(throwable.message.toString())
        isRefreshing.value = false
    }

    init {
        getAllTheFeeds()
    }

    fun refreshFeeds() {
        getAllTheFeeds(true)
    }

    private fun getAllTheFeeds(isRefresh: Boolean = false) {
        viewModelScope.launch(exceptionHandler) {
            if (isRefresh.not()) _feedsStateFlow.emit(UiState.Loading)
            val postModel: PostModel = repository.getAllThePosts()
            _feedsStateFlow.emit(UiState.Success(postModel))
            println("sdfa viewmode success")

            if (isRefresh) isRefreshing.value = false

        }
    }

    fun feedsItemClicked(stream: Stream) {
        viewModelScope.launch {
            navigationEvent.emit(
                Screens.Detail.withArgs(
                    stream.id
                )
            )
        }
    }

}