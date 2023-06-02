package com.example.mypostapp.domain.model

data class PostModel(
    val id: Int,
    val description: String,
    val imageUrl: String,
    val color: PostColor,
    val date: Long
)

enum class PostColor() {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    GREY
}