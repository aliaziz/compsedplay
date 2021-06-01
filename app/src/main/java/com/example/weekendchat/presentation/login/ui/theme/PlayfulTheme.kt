package com.example.weekendchat.presentation.login.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColors = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Red,
    secondaryVariant = Color.Yellow,
    background = Color.White
)

private val lightColors = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Color.Red,
    secondaryVariant = Color.Yellow,
    background = Color.White
)

@Composable
fun PlayfulTheme(darkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkMode) darkColors
    else lightColors
    MaterialTheme(colors, MaterialTheme.typography) {
        content()
    }
}