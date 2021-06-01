package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState

interface SingleUseCase<in Input, Output> {
    operator fun invoke(input: Input): LiveData<ResultState<Output>>
}