package com.example.weekendchat.di.login

import com.example.weekendchat.data.datasource.LoginRepoImpl
import com.example.weekendchat.data.datasource.VerificationRepoImpl
import com.example.weekendchat.domain.contract.LoginRepository
import com.example.weekendchat.domain.contract.VerificationRepository
import dagger.Binds
import dagger.Module

@Module
interface LoginRepoModule {
    @Binds
    fun loginRepository(loginRepoImpl: LoginRepoImpl): LoginRepository

    @Binds
    fun verificationRepository(verificationRepoImpl: VerificationRepoImpl): VerificationRepository
}