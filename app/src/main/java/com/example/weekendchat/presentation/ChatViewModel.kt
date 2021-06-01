package com.example.weekendchat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weekendchat.di.scope.ActivityScope
import com.example.weekendchat.domain.entity.ChatHistory
import com.example.weekendchat.domain.usecase.GetChatHistoryUseCase
import com.example.weekendchat.domain.usecase.SendMessageUseCase
import com.example.weekendchat.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ActivityScope
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getChatHistoryUseCase: GetChatHistoryUseCase,
    private val getChatSendMessageUseCase: SendMessageUseCase
): ViewModel() {
    private var _inputString = MutableStateFlow("")
    val inputString: StateFlow<String> = _inputString

    fun getChatHistory(chatId: Long): LiveData<UIState<ChatHistory>> {
        return Transformations.map(getChatHistoryUseCase(chatId)) {
            UIState.toUIState(it)
        }
    }
}