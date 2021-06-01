package com.example.weekendchat.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.weekendchat.presentation.LoginViewModel
import com.example.weekendchat.presentation.home.ChatUIActivity
import com.example.weekendchat.presentation.login.ui.theme.WeekendChatTheme
import com.example.weekendchat.presentation.state.UIState

class VerificationActivity : BaseAuthActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel.setDefault(LoginViewModel.AuthType.VERIFY)
        setContent {
            AuthUI {
                WeekendChatTheme {
                    SetupUI(loginViewModel) { input ->
                        loginViewModel.verify(input).observe(this) { state ->
                            when (state) {
                                is UIState.Success -> launchChat()
                                is UIState.Error -> TODO()
                                is UIState.Loading -> TODO()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun launchChat() {
        Intent(this, ChatUIActivity::class.java).apply {
            startActivity(this)
        }
    }
}