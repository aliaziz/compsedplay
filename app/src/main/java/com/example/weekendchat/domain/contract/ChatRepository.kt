package com.example.weekendchat.domain.contract

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ChatSummary
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.entity.ChatHistory

interface ChatRepository {
    fun sendMessage(text: String): LiveData<ResultState<Boolean>>
    fun getChatHistory(chatId: Long): LiveData<ResultState<ChatHistory>>
    fun getChatSummary(): LiveData<ResultState<List<ChatSummary>>>
}
