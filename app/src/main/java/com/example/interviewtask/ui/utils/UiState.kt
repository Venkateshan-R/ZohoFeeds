package com.example.interviewtask.ui.utils

sealed class UiState<out T> {
    class Success<T>(val data: T) : UiState<T>()
    class Failure(val error: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()

}