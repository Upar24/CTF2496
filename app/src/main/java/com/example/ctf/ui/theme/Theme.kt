package com.example.ctf.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ctf.ui.auth.AuthViewModel

private val DarkColorPalette = darkColors(
    primary= uranianblue,
    primaryVariant= frenchskyblue,
    surface = CGblue,
    secondary = indigodye,
    secondaryVariant = Color.Blue,
    onBackground = whiteblue ,
    error = Color.Yellow,
    onError = Color.Green,

)
private val LightColorPalette = lightColors(
    primary=Color.Blue,
    primaryVariant= ultramarineblue,
    surface = dodgerblue,
    secondary = frenchskyblue,
    secondaryVariant = uranianblue,
    onBackground = darkblue,
    error = yellowcustom,
    onError = greencustom,
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
    var darkModeState by mutableStateOf(false)
}