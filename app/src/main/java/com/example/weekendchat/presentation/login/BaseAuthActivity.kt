package com.example.weekendchat.presentation.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.weekendchat.WeekendChatApplication
import com.example.weekendchat.di.login.LoginSubGraph
import com.example.weekendchat.presentation.LoginViewModel
import javax.inject.Inject

open class BaseAuthActivity : ComponentActivity() {

    @Inject
    lateinit var loginViewModel: LoginViewModel
    private lateinit var loginSubGraph: LoginSubGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        loginSubGraph =
            (applicationContext as WeekendChatApplication).getAppGraph().loginSubGraph().create()
        loginSubGraph.inject(this)
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun AuthUI(theme: @Composable () -> Unit) {
        theme()
    }

    @Composable
    fun SetupUI(
        loginViewModel: LoginViewModel,
        response: (String) -> Unit
    ) {
        val inputString by remember { loginViewModel.inputString }.observeAsState()
        val defaultConfig by loginViewModel.defaultData.observeAsState()
        val (text, setText) = remember { mutableStateOf("") }

        defaultConfig?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    IntroContent(text = data.introText)
                    Spacer(modifier = Modifier.padding(8.dp))
                    InputField(placeHolder = data.placeholder, text) {
                        loginViewModel.onChange(it)
                        setText(it)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                }
                ActionButton(text = data.buttonText, inputString?.isNotEmpty() ?: false) {
                    inputString?.let {
                        response(it)
                    }
                }
            }
        }

    }

    @Composable
    fun IntroContent(text: String) {
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colors.surface, RoundedCornerShape(5.dp))
                .border(BorderStroke(0.1f.dp, MaterialTheme.colors.primary)),
            fontWeight = FontWeight.Medium,
            style = TextStyle.Default.copy(fontFamily = FontFamily.Monospace)
        )
    }

    @Composable
    fun InputField(placeHolder: String, text: String, onTextChanged: (String) -> Unit) {
        TextField(value = text,
            onValueChange = onTextChanged,
            placeholder = {
            Text(text = placeHolder)
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
    }

    @Composable
    fun ActionButton(text: String, isEnabled: Boolean, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            enabled = isEnabled,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = text)
        }
    }
}
