package com.example.ctf.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ctf.R

val BarlowSemi= FontFamily(
    Font(R.font.b400),
    Font(R.font.b100,FontWeight.W100),
    Font(R.font.b200,FontWeight.W200),
    Font(R.font.b300,FontWeight.W300),
    Font(R.font.b500,FontWeight.W500),
    Font(R.font.b600,FontWeight.W600),
    Font(R.font.b700,FontWeight.W700),
    Font(R.font.b800,FontWeight.W800),
    Font(R.font.b900,FontWeight.W900),
)
val Barlow = FontFamily(
    Font(R.font.b400reguler),
    Font(R.font.b100thin,FontWeight.W100),
    Font(R.font.b200light,FontWeight.W200),
    Font(R.font.b300light,FontWeight.W300),
    Font(R.font.b500medium,FontWeight.W500),
    Font(R.font.b600bold,FontWeight.W600),
    Font(R.font.b700bold,FontWeight.W700),
    Font(R.font.b800bold,FontWeight.W800),
    Font(R.font.b900bold,FontWeight.W900),
)
val Typography = Typography(defaultFontFamily = Barlow,
    h1 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
    h2 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W300,
        fontSize = 32.sp
    ),
    h3 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W300,
        fontSize = 30.sp
    ),
    h4 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W300,
        fontSize = 28.sp
    ),
    h5 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W600,
        fontSize = 26.sp,
        textAlign = TextAlign.Center
    ),
    h6 =TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W300,
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    body1 = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp
    ),
    body2 = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp
    ),
    caption = TextStyle(
        fontFamily = Barlow,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )
)