package com.example.mypostapp.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypostapp.presentation.model.PostModel
import java.util.*


@Composable
fun ItemNoteCard(
    postModel: PostModel,
    onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 24.dp)
            .padding(end = 24.dp)
            .padding(top = 18.dp)
            .clickable(onClick = onClick),
        backgroundColor = postModel.color,
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.Black,
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ItemImageNote(postModel = postModel)
            Spacer(modifier = Modifier.width(16.dp))
            ItemNoteData(postModel = postModel)
        }
    }
}


@Composable
fun ItemImageNote(postModel: PostModel) {
    Image(
        painter = painterResource(id = postModel.image),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}


@Composable
fun ItemNoteData(
    postModel: PostModel
) {
    val currentDate = Date()
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
            fontSize = 10.sp,
            fontWeight = FontWeight.Light,
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
                text = postModel.date.format(currentDate),
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}