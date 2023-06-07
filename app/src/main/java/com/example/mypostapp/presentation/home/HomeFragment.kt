package com.example.mypostapp.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mypostapp.R
import com.example.mypostapp.domain.model.PostModel
import java.util.*


class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    topBar = {
                        HomeTopAppBar()
                    },
                    floatingActionButton = {
                        Fab {
                            findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                        }
                    },
                    floatingActionButtonPosition = FabPosition.End
                ) {
                    viewModel.getAllPosts()

                    val currentList: List<PostModel> by viewModel.postModels.collectAsState(
                        emptyList()
                    )
                    LazyColumn(
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 16.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(currentList) { model ->
                            ItemNoteCard(
                                postModel = model,
                                onClick = {
                                    val direction =
                                        HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                                            postId = model.id
                                        )
                                    findNavController().navigate(direction)
                                })
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }
        }
    }



}

@Composable
fun HomeTopAppBar() {
    val appColor = colorResource(id = R.color.violet)
    TopAppBar(
        contentColor = Color.White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "My posts",
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
fun Fab(clickListener: () -> Unit) {
    val appColor = colorResource(id = R.color.violet)
    FloatingActionButton(
        modifier = Modifier
            .padding(16.dp),
        onClick = {
            clickListener()
        },
        contentColor = Color.White,
        backgroundColor = appColor,
        content = {
            Icon(imageVector = Icons.Filled.Create, contentDescription = "Add")
        }
    )
}






