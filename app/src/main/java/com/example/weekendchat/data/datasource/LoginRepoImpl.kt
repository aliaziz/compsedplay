package com.example.weekendchat.data.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weekendchat.data.entity.ResultState
import com.example.weekendchat.domain.contract.LoginRepository
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LoginRepoImpl @Inject constructor(private val loginDataSource: LoginDataSource) :
    LoginRepository {
    override fun login(phone: String): LiveData<ResultState<Boolean>> {
        return Transformations.map(loginDataSource.login()) {
            return@map if (it) ResultState.Success(it)
            else ResultState.Error(Exception("Failed to login"))
        }
    }

}