package com.example.weekendchat.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable


@Composable
fun ChatTopBar(title: String, onNavClick: () ->  Unit) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = onNavClick) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}