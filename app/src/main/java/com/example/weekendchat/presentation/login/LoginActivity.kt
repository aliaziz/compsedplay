package com.example.weekendchat.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.weekendchat.presentation.LoginViewModel
import com.example.weekendchat.presentation.login.ui.theme.PlayfulTheme
import com.example.weekendchat.presentation.state.UIState

class LoginActivity : BaseAuthActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel.setDefault(LoginViewModel.AuthType.LOGIN)
        setContent {
            AuthUI {
                PlayfulTheme {
                    SetupUI(loginViewModel = loginViewModel) { input ->
                        loginViewModel.login(input).observe(this) { state ->
                            when (state) {
                                is UIState.Success -> moveToVerify()
                                is UIState.Error -> showError(state.exception)
                                is UIState.Loading -> TODO()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showError(exception: Exception) {
        print(exception.localizedMessage)
    }

    private fun moveToVerify() {
        startActivity(Intent(this, VerificationActivity::class.java))
    }
}