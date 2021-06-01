package com.example.weekendchat.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.VerificationRepository
import javax.inject.Inject

class VerificationRepoImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : VerificationRepository {
    override fun verify(code: String): LiveData<ResultState<Boolean>> {
        return Transformations.map(loginDataSource.verify(code)) {
            return@map ResultState.Success(it)
        }
    }
}