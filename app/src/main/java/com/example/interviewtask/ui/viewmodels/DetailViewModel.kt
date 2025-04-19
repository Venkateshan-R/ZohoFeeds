package com.example.interviewtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtask.data.models.Stream
import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: FeedsRepository) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<Boolean>()
    val navigationEvent = _navigationEvent as SharedFlow<Boolean>

    private val _moreEvent = MutableSharedFlow<Boolean>()
    val moreEvent = _moreEvent as SharedFlow<Boolean>

    private val _selectedFeedStateFlow: MutableStateFlow<UiState<Stream>> =
        MutableStateFlow(UiState.Loading)
    val selectedFeedStateFlow = _selectedFeedStateFlow as StateFlow<UiState<Stream>>

    var selectedId: String = ""

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _selectedFeedStateFlow.value = UiState.Failure(throwable.message.toString())
    }

    fun getSelectedFeed() {
        viewModelScope.launch(exceptionHandler) {
            _selectedFeedStateFlow.emit(UiState.Loading)
            val stream = repository.getFeedById(selectedId)
            _selectedFeedStateFlow.emit(UiState.Success(stream))
        }
    }



    fun onBackClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(true)
        }
    }

    fun showMore(){
        viewModelScope.launch {
            _moreEvent.emit(true)
        }
    }

    fun hideMore(){
        viewModelScope.launch {
            _moreEvent.emit(false)
        }
    }
}