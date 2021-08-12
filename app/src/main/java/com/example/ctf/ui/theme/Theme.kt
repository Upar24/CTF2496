package com.example.ctf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary =blue8,
    primaryVariant =blue7,
    secondary = blue9,
    secondaryVariant=blue6,
    background = blue11,
    surface = blue7,
    error= Color.Red,
    onPrimary = blue1,
    onSecondary = blue0,
    onBackground = blue4,
    onSurface = blue1,
    onError = blue12
)
private val LightColorPalette = lightColors(
    primary = blue4,
    primaryVariant = blue8,
    secondary = blue5,
    secondaryVariant = blue6,
    background = white,
    surface = blue7,
    error = Color.Red,
    onPrimary = blue11,
    onSecondary = blue12,
    onBackground = blue12,
    onSurface = blue11,
    onError = blue3
)

@Composable
fun CTFTheme(
    isDark: Boolean =ThemeState.darkModeState,
    content: @Composable () -> Unit
) {
    val colors = if(isDark) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
object ThemeState{
    var darkModeState by mutableStateOf(true)
}