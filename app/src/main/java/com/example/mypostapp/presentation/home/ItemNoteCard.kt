package com.example.mypostapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mypostapp.domain.model.PostModel
import com.example.mypostapp.presentation.getColorFromPostColor
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ItemNoteCard(
    postModel: PostModel,
    onClick: () -> Unit
) {

    val backgroundColor: Color = getColorFromPostColor(postModel.color)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier.background(backgroundColor)
        ) {
            ItemImageNote(postModel = postModel)
            Spacer(modifier = Modifier.width(16.dp))
            ItemNoteData(postModel = postModel)
        }
    }
}


@Composable
fun ItemImageNote(postModel: PostModel) {
    AsyncImage(
        model = postModel.imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}


@Composable
fun ItemNoteData(
    postModel: PostModel
) {
    val currentDate = postModel.date
    val formatDate = convertTimestampToDateTime(timestamp = currentDate)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        Text(
            text = postModel.description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = formatDate,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun convertTimestampToDateTime(timestamp: Long): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
    val date = Date(timestamp)
    return dateFormat.format(date)
}