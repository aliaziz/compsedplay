package com.example.weekendchat.presentation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weekendchat.WeekendChatApplication
import com.example.weekendchat.domain.entity.Chat
import com.example.weekendchat.presentation.ChatViewModel
import com.example.weekendchat.presentation.components.ChatTopBar
import com.example.weekendchat.presentation.login.ui.theme.WeekendChatTheme
import com.example.weekendchat.presentation.state.UIState

import javax.inject.Inject

class ChatDetailsUI : AppCompatActivity() {
    @Inject
    lateinit var chatViewModel: ChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeekendChatApplication)
            .getAppGraph()
            .chatSubGraph()
            .create()
            .inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            WeekendChatTheme {
                ChatDetailsContent()
            }
        }
    }

    @Composable
    private fun ChatDetailsContent() {
        Scaffold(
            topBar = {
                ChatTopBar(
                    title = "Mary Le boo"
                ) { finish() }
            },
            bottomBar = {
                val (text, setText) = remember { mutableStateOf("") }
                ChatInputTextField(text, setText) { message ->
                    onSendMessage(message)
                }
            }
        ) {
//            val bundle = intent.extras
            val chatId = 1L
            val result: LiveData<List<Chat>> =
                Transformations.map(chatViewModel.getChatHistory(chatId)) {
                    when (it) {
                        is UIState.Success -> it.data.history
                        else -> emptyList()
                    }
                }
            val history by remember { result }.observeAsState()
            history?.let { it1 -> ChatHistoryBody(it1) }
        }
    }

    @Composable
    private fun ChatHistoryBody(chats: List<Chat>) {

        LazyColumn(modifier = Modifier.padding(8.dp)) {
            item {
                Divider(
                    startIndent = 1.dp,
                    color = MaterialTheme.colors.surface.copy(alpha = ContentAlpha.medium)
                )
            }

            items(chats.size) { index ->
                ChatItem(chats[index])
            }
        }
    }

    @Composable
    private fun ChatItem(chat: Chat) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(2.dp)
                    .wrapContentWidth(
                        if (chat.isReceived)
                            Alignment.Start else Alignment.End
                    )
                    .background(
                        color = if (chat.isReceived) Color.White
                        else Color(0xFFBBDEFB),
                        shape = RoundedCornerShape(5.dp)
                    )
                    .padding(5.dp)
            ) {
                Text(
                    chat.textMessage,
                    modifier = Modifier,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    "12:31pm",
                    color = Color.Black.copy(0.4f),
                    modifier = Modifier
                        .defaultMinSize(2.dp)
                        .wrapContentWidth(Alignment.End),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }

    @Preview
    @Composable
    fun PrevChatLeftItem() {
        ChatItem(chat = Chat(1, "Welcome", 1, 1, null, null, false))
    }

    @Preview
    @Composable
    fun PrevChatRightItem() {
        ChatItem(chat = Chat(1, "Welcome", 1, 1, null, null, true))
    }


    @Composable
    private fun ChatInputTextField(
        text: String,
        onTextChange: (String) -> Unit,
        onSendMessage: (String) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                placeholder = {
                    Text(text = "Here")
                },
                maxLines = 8,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            TextButton(
                onClick = { onSendMessage(text) },
                enabled = text.isNotEmpty(),
            ) {
                Text(text = "Send")
            }
        }
    }

    private fun onSendMessage(message: String) {
        chatViewModel
    }
}
