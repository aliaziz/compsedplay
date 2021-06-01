package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
): SingleUseCase<String, Boolean> {
    override fun invoke(input: String): LiveData<ResultState<Boolean>> {
        return chatRepository.sendMessage(input)
    }
}