package com.example.weekendchat

import android.app.Application
import com.example.weekendchat.di.AppGraph
import com.example.weekendchat.di.DaggerAppGraph

class WeekendChatApplication : Application() {
    private lateinit var appGraph: AppGraph

    override fun onCreate() {
        appGraph = DaggerAppGraph.builder().application(this).build()
        super.onCreate()
    }

    fun getAppGraph() = appGraph
}