package com.example.weekendchat.data.entity

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>() //Declaration site variance. That's why the out is taken in as
    // a parameter in the class constructor. Telling the user that am giving you this object, but you can only produce it,
    // not consume it 😎
    data class Error(val error: Exception) : ResultState<Nothing>()
}