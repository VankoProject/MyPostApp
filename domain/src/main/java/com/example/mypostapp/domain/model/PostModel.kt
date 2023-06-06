package com.example.mypostapp.domain.model

data class PostModel(
    val id: Long,
    val description: String,
    val imageUrl: String,
    val color: String,
    val date: Long
) {

}

enum class PostColor() {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    GREY
}




