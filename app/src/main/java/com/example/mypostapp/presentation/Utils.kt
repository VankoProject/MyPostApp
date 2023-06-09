package com.example.mypostapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.mypostapp.domain.model.PostColor

@Composable
fun getColorFromPostColor(colorString: String): Color {
    return when (PostColor.valueOf(colorString)) {
        PostColor.RED -> Color.Red.copy(alpha = 0.5f)
        PostColor.GREEN -> Color.Green.copy(alpha = 0.5f)
        PostColor.BLUE -> Color.Blue.copy(alpha = 0.5f)
        PostColor.GREY -> Color.Gray.copy(alpha = 0.5f)
        PostColor.YELLOW -> Color.Yellow.copy(alpha = 0.5f)
    }
}