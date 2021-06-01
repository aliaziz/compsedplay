package com.example.weekendchat.domain.usecase

import androidx.lifecycle.LiveData
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.VerificationRepository
import javax.inject.Inject

class VerificationUseCase @Inject constructor(
    private val verificationRepository: VerificationRepository
) : SingleUseCase<String, Boolean> {
    override fun invoke(input: String): LiveData<ResultState<Boolean>> {
        return verificationRepository.verify(input)
    }
}