package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ChatSummary
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.ChatRepository
import javax.inject.Inject

class GetChatSummaryUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): SingleUseCase<Unit, List<ChatSummary>> {
    override fun invoke(input: Unit): LiveData<ResultState<List<ChatSummary>>> {
        return chatRepository.getChatSummary()
    }
}