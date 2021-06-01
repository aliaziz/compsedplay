package com.example.weekendchat.di

import android.app.Application
import com.example.weekendchat.di.chat.ChatSubGraph
import com.example.weekendchat.di.data.DBModule
import com.example.weekendchat.di.login.LoginSubGraph
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DBModule::class,
        LoginSubGraph.LoginSubGraphModule::class,
        ChatSubGraph.ChatSubGraphModule::class
    ], dependencies = [Application::class] //Unnecessary, because we have a setter for adding this dependency i.e inject
)
interface AppGraph {
    fun loginSubGraph(): LoginSubGraph.Factory
    fun chatSubGraph(): ChatSubGraph.Factory
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun appContext(application: Application): Builder
//        fun build(): AppGraph
//    }

}