package com.example.weekendchat.data.datasource

import com.example.weekendchat.data.ChatDB
import com.example.weekendchat.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ChatLocalDataSource @Inject constructor(private val db: ChatDB) {

    fun getChatHistory(chatId: Long) = db.chatDao().getChatHistory(chatId)
}