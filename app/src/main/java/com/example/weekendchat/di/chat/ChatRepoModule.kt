package com.example.weekendchat.di.chat

import com.example.weekendchat.data.datasource.ChatRepoImpl
import com.example.weekendchat.domain.contract.ChatRepository
import dagger.Binds
import dagger.Module

@Module
interface ChatRepoModule {
    @Binds
    fun bind(chatRepoImpl: ChatRepoImpl): ChatRepository
}