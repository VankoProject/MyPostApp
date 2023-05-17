package com.example.mypostapp.presentation.home

import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mypostapp.R

@Preview
@Composable
fun NoteCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 24.dp)
            .padding(end = 24.dp)
            .padding(top = 18.dp),
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.Black,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ImageNote()
            Spacer(modifier = Modifier.width(16.dp))
            NoteData(
                title = "It is the post of this list with some notes",
                date = "19:38 am 09.10.2023"
            )
        }
    }
}


@Composable
fun ImageNote() {
    Image(
        painter = painterResource(id = R.drawable.image_note),
        contentDescription = null,
        contentScale = ContentScale.Fit
    )
}


@Composable
fun NoteData(
    title: String,
    date: String
) {
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
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .fillMaxWidth(),
            maxLines = 2
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = date,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}