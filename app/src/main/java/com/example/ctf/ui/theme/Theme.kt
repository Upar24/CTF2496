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
    primary = bluewhite,
    primaryVariant= tintdark,
    secondary = dongkerlight,
    secondaryVariant= sky,
    background = black,
    surface = blue1,
    onPrimary = whitedongkerlight,
    onSecondary = whitedonker,
    onBackground = bluewhite,
    onSurface = whitedongkerlight,
    error = Color.Red,
    onError = Color.Blue
)
private val LightColorPalette = lightColors(
    primary = skylight,
    primaryVariant=Color.Blue,
    secondary = sky,
    secondaryVariant = blue,
    background = white,
    surface = whitesurface,
    onPrimary = dongker,
    onSecondary = dongker,
    onBackground = black,
    onSurface = dongker,
    error=testing,
    onError =onerror
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