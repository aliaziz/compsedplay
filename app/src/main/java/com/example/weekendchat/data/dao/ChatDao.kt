package com.example.weekendchat.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.weekendchat.data.entity.ChatHistory

@Dao
interface ChatDao {
    @Query("SELECT * FROM ChatHistory")
    fun getAllChatHistory(): LiveData<List<ChatHistory>>

    @Query("SELECT * FROM ChatHistory WHERE chatId = :forId")
    fun getChatHistory(forId: Long): LiveData<List<ChatHistory>>
}