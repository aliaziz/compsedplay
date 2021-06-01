package com.example.weekendchat.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weekendchat.di.scope.ActivityScope
import com.example.weekendchat.domain.usecase.LoginUseCase
import com.example.weekendchat.domain.usecase.VerificationUseCase
import com.example.weekendchat.presentation.login.ui.DefaultData
import com.example.weekendchat.presentation.state.UIState
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase,
    val verificationUseCase: VerificationUseCase
) : ViewModel() {

    private var _inputString = MutableLiveData<String>()
    val inputString: LiveData<String> = _inputString

    private var _defaultData = MutableLiveData<DefaultData>()
    val defaultData: LiveData<DefaultData> = _defaultData

    fun setDefault(authType: AuthType) {
        when (authType) {
            AuthType.LOGIN -> _defaultData.value = DefaultData(
                "Login",
                "E.g 7021111",
                "Enter phone number"
            )
            AuthType.VERIFY -> _defaultData.value = DefaultData(
                "Verify",
                "E.g 9090",
                "Enter code"
            )
        }
    }

    fun onChange(input: String) {
        _inputString.value = input
    }

    fun login(phone: String): LiveData<UIState<Boolean>> {
        return Transformations.map(loginUseCase(phone)) { UIState.toUIState(it) }
    }

    fun verify(code: String): LiveData<UIState<Boolean>> {
        return Transformations.map(verificationUseCase(code)) { UIState.toUIState(it) }
    }

    enum class AuthType {
        LOGIN, VERIFY
    }

}


