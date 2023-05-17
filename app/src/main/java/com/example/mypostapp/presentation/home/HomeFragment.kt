package com.example.mypostapp.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.mypostapp.R


class HomeFragment : Fragment() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        TopAppBar()
                    },
                    floatingActionButton = {
                        Fab()
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        NoteCard()
                        NoteCard()
                        NoteCard()
                        NoteCard()
                        NoteCard()
                        NoteCard()
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar() {
    val appColor = colorResource(id = R.color.violet)
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "My posts",
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        },
        backgroundColor = appColor,
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    Icons.Default.MoreVert,
                    "Drop-down menu",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun Fab() {
    val appColor = colorResource(id = R.color.violet)
    FloatingActionButton(
        modifier = Modifier
            .padding(16.dp),
        onClick = {

        },
        contentColor = Color.White,
        backgroundColor = appColor,
        content = {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
        }
    )
}






