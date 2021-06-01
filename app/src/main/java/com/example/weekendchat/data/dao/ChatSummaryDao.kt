package com.example.weekendchat.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.weekendchat.data.entity.ChatSummary

@Dao
interface ChatSummaryDao {
    @Query("SELECT * FROM ChatSummary")
    fun fetchSummary(): LiveData<List<ChatSummary>>
}