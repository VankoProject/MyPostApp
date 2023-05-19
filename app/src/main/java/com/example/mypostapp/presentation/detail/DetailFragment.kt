package com.example.mypostapp.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mypostapp.R


class DetailFragment : Fragment() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(
                    topBar = {
                        DetailTopAppBar(
                            onBackButtonClick = {
                                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
                            },
                            onAddButtonClick = {
                                TODO("add post to database")
                            }
                        )
                    }
                ) {

                }
            }
        }
    }
}

@Composable
private fun DetailTopAppBar(
    onBackButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit
) {
    val appColor = colorResource(id = R.color.violet)
    TopAppBar(
        backgroundColor = appColor,
        contentColor = Color.White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Note's details",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }

        },
        navigationIcon = {
            IconButton(onClick = {
                onBackButtonClick()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = {
                onAddButtonClick()
            }) {
                Icon(Icons.Filled.Check, contentDescription = null)
            }
        }
    )
}

