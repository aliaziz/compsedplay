package com.example.weekendchat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatHistory(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val chatId: Long,
    val message: String,
    val from: Long,
    val to: Long,
    val isReceived: Boolean,
    val imageUrl: String?,
    val link: String?
)