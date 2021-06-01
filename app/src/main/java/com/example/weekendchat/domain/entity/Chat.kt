package com.example.weekendchat.domain.entity

data class Chat(
    val chatId: Long,
    val textMessage: String,
    val to: Long,
    val from: Long,
    val link: String?,
    val imageUrl: String?,
    val isReceived: Boolean = false
)
