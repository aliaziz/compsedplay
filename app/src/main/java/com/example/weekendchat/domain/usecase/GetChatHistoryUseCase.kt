package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.ChatRepository
import com.example.weekendchat.domain.entity.ChatHistory
import javax.inject.Inject

class GetChatHistoryUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) : SingleUseCase<Long, ChatHistory> {
    override fun invoke(input: Long): LiveData<ResultState<ChatHistory>> {
        return chatRepository.getChatHistory(input)
    }
}