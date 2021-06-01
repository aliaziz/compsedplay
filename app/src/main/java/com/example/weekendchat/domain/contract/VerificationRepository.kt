package com.example.weekendchat.domain.contract

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState

interface VerificationRepository {
    fun verify(code: String): LiveData<ResultState<Boolean>>
}