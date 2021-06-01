package com.example.weekendchat.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weekendchat.data.dao.ChatDao
import com.example.weekendchat.data.dao.ChatSummaryDao
import com.example.weekendchat.data.entity.ChatHistory
import com.example.weekendchat.data.entity.ChatSummary
import java.util.*

@Database(
    entities = [ChatHistory::class, ChatSummary::class],
    version = 2,
    exportSchema = true
)
abstract class ChatDB : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun chatSummaryDao(): ChatSummaryDao
}