package com.example.ctf.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ctf.R


val Sans = FontFamily(
    Font(R.font.sansreguler400),
    Font(R.font.sansbold700,FontWeight.W700),
    Font(R.font.sanslight300,FontWeight.W300),
    Font(R.font.semibold600,FontWeight.W600),
)
val Typography = Typography(defaultFontFamily = Sans,
    h1 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp
    ),
    h2 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp
    ),
    h3 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h4 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W300,
        fontSize = 28.sp
    ),
    h5 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    ),
    h6 =TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W300,
        fontSize = 24.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Sans,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    )
)