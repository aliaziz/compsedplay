package com.example.weekendchat.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weekendchat.data.entity.ChatSummary
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.ChatRepository
import com.example.weekendchat.domain.entity.Chat
import com.example.weekendchat.domain.entity.ChatHistory
import javax.inject.Inject

class ChatRepoImpl @Inject constructor(
    private val localDataSource: ChatLocalDataSource
) : ChatRepository {
    override fun sendMessage(text: String): LiveData<ResultState<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getChatHistory(chatId: Long): LiveData<ResultState<ChatHistory>> {
        return Transformations.map(localDataSource.getChatHistory(chatId)) {
            val chatList = it.map { history ->
                Chat(
                    history.chatId,
                    history.message,
                    history.to,
                    history.from,
                    history.link,
                    history.imageUrl,
                    history.isReceived
                )
            }
            ResultState.Success(ChatHistory(chatList))
        }
    }

    override fun getChatSummary(): LiveData<ResultState<List<ChatSummary>>> {
        TODO("Not yet implemented")
    }

}
