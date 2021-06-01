package com.example.weekendchat.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatSummary(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val chatId: Long,
    val to: Long,
    val from: Long,
    val lastMessage: String
)