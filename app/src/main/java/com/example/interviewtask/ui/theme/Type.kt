package com.example.interviewtask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.interviewtask.R

// Set of Material typography styles to start with

val customFontFamily = FontFamily(
    Font(R.font.extralight, FontWeight.ExtraLight),
    Font(R.font.light, FontWeight.Light),
    Font(R.font.regular, FontWeight.Normal),
    Font(R.font.medium, FontWeight.Medium),
    Font(R.font.bold, FontWeight.Bold),
    Font(R.font.semibold, FontWeight.SemiBold),
)

val Typography = Typography(

    titleMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 22.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp

    ),

    labelLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    labelMedium = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp

    ),

    labelSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp

    ),

    titleSmall = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    titleLarge = TextStyle(
        fontFamily = customFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 26.sp,
    ),

)