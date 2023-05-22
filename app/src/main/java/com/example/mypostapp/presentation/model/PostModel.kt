package com.example.mypostapp.presentation.model

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat


data class PostModel(
    val id: Int,
    val description: String,
    val image: Int,
    val color: Color,
    val date: SimpleDateFormat
)

enum class PostColor() {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    GREY
}

