package com.example.weekendchat.presentation.state

import com.example.weekendchat.data.entity.ResultState

sealed class UIState<out T> {
    data class Loading<T>(val load: T) : UIState<T>()
    data class Error(val exception: Exception) : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()

    companion object {
        inline fun <reified E> toUIState(resultState: ResultState<E>): UIState<E> {
           return when (resultState) {
                is ResultState.Error -> Error(resultState.error)
                is ResultState.Success -> Success(resultState.data)
            }
        }
    }
}