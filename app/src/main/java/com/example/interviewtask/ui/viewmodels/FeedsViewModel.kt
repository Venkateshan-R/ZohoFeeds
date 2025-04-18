package com.example.interviewtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtask.data.models.FeedsModel
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.ui.navigation.Screens
import com.example.interviewtask.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FeedsViewModel @Inject constructor(val repository: FeedsRepository) : ViewModel() {

    private val _isRefreshing = MutableStateFlow<Boolean>(false)
    val isRefreshing = _isRefreshing as StateFlow<Boolean>

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent as SharedFlow<String>

    private val _feedsStateFlow: MutableStateFlow<UiState<FeedsModel>> =
        MutableStateFlow(UiState.Loading)
    val feedsStateFlow = _feedsStateFlow as StateFlow<UiState<FeedsModel>>

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _feedsStateFlow.value = UiState.Failure(throwable.message.toString())
        viewModelScope.launch {
            delay(300)
            _isRefreshing.emit(false)
        }
    }

    init {
        getAllTheFeeds()
    }

    fun refreshFeeds() {
        _isRefreshing.value = true
        getAllTheFeeds(true)
    }

    private fun getAllTheFeeds(isRefresh: Boolean = false) {
        viewModelScope.launch(exceptionHandler) {
            if (isRefresh.not()) _feedsStateFlow.emit(UiState.Loading)
            val feedsModel: FeedsModel = repository.fetchAllTheFeeds()
            _feedsStateFlow.emit(UiState.Success(feedsModel))

            if (isRefresh) _isRefreshing.value = false

        }
    }

    fun feedsItemClicked(stream: Stream) {
        viewModelScope.launch {
            _navigationEvent.emit(
                Screens.Detail.withArgs(
                    stream.id
                )
            )
        }
    }
}