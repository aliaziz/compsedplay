package com.example.weekendchat.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weekendchat.data.entity.ChatSummary
import com.example.weekendchat.presentation.components.ChatTopBar
import com.example.weekendchat.presentation.login.ui.theme.WeekendChatTheme

class ChatUIActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val list = mutableStateListOf(ChatSummary(1, 1, 1, 1, "Welcome"))
            WeekendChatTheme {
                Scaffold(
                    topBar = { ChatTopBar("Chat Summary") { finish() } },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(Icons.Filled.Chat, contentDescription = null)
                        }
                    }) {
                    ChatSummaryContent(list)
                }
            }
        }
    }

    @Composable
    private fun ChatSummaryContent(summary: List<ChatSummary>) {
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState) {
            items(summary.size) { id ->
                val chatSummary = summary[id]
                ChatSummaryItem(chatSummary) { launchChatDetails(chatSummary.chatId) }
            }
        }
    }

    private fun launchChatDetails(chatId: Long) {
        Intent(this, ChatDetailsUI::class.java).apply {
            putExtra("chatId", chatId)
            startActivity(this)
        }
    }

    @Composable
    fun ChatSummaryItem(chatSummary: ChatSummary, action: () -> Unit) {
        Row(
            Modifier
                .clickable { action() }
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(0.2f)
            ) {}
            Column(
                Modifier
                    .fillMaxWidth(0.9f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = chatSummary.from.toString(),
                    fontWeight = FontWeight.Bold
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        chatSummary.lastMessage,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null)
        }
    }

    @Preview
    @Composable
    fun preview() {
        WeekendChatTheme {
            Scaffold(topBar = { ChatTopBar("Chat Summary", {}) }, floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        Icons.Filled.Chat, contentDescription = null,
                        tint = Color.White
                    )
                }
            }) {
                ChatSummaryContent(
                    listOf(
                        ChatSummary(1, 1, 1, 1, "Welcome"),
                        ChatSummary(1, 1, 1, 1, "Welcome")
                    )
                )
            }
        }
    }
}
