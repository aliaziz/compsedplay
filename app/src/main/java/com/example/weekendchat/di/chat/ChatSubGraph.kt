package com.example.weekendchat.di.chat

import com.example.weekendchat.di.scope.ActivityScope
import com.example.weekendchat.presentation.home.ChatDetailsUI
import com.example.weekendchat.presentation.home.ChatUIActivity
import dagger.Module
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ChatRepoModule::class])
interface ChatSubGraph {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChatSubGraph
    }

    fun inject(chatDetailsUI: ChatDetailsUI)
    fun inject(chatUIActivity: ChatUIActivity)

    @Module(subcomponents = [ChatSubGraph::class])
    class ChatSubGraphModule {}
}
