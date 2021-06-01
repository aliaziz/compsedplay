package com.example.weekendchat.domain.contract

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.di.scope.ActivityScope

@ActivityScope
interface LoginRepository {
    fun login(phone: String): LiveData<ResultState<Boolean>>
}