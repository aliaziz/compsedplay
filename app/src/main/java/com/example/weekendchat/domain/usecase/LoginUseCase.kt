package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : SingleUseCase<String, Boolean> {
    override fun invoke(input: String): LiveData<ResultState<Boolean>> = loginRepository.login(input)
}