package com.example.mypostapp.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.mypostapp.R
import com.example.mypostapp.presentation.model.PostColor
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val colorItems = PostColor.values()
                var selectedColor by remember { mutableStateOf(colorItems[0]) }
                var expanded by remember { mutableStateOf(false) }
                var noteText by remember { mutableStateOf("") }

                val snackbarHostState = remember {
                    SnackbarHostState()
                }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    },
                    topBar = {
                        DetailTopAppBar(
                            onBackButtonClick = {
                                lifecycleScope.launch {
                                   val action = snackbarHostState.showSnackbar(
                                        message = "Do you want to save post?",
                                        actionLabel = "Save post",
                                        duration = SnackbarDuration.Long)
                                    if (action == SnackbarResult.ActionPerformed) {
                                        findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
                                    }
                                }
                            },
                            onAddButtonClick = {

                            }
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .background(Color.Red.copy(alpha = 0.5f))
                        ) {
                            LazyRow {
                                item { }
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Select background color for your post",
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Light
                            )
                            Spacer(modifier = Modifier.padding(bottom = 8.dp))
                            SelectColorForPost(
                                selectedColor = selectedColor,
                                onColorSelected = { color -> selectedColor = color },
                                expanded = expanded,
                                onExpandedChange = { expanded = it },
                                colorItems = colorItems
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        NoteInput(onNoteEntered = { note ->
                            noteText = note
                        })
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectColorForPost(
    selectedColor: PostColor,
    onColorSelected: (PostColor) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    colorItems: Array<PostColor>,
) {
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Box {
        OutlinedTextField(
            value = TextFieldValue(text = selectedColor.name),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = getColor(color = selectedColor)),
            trailingIcon = {
                Icon(icon, null, Modifier.clickable {
                    onExpandedChange(!expanded)
                })
            },
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            colorItems.forEach { colorItem ->
                DropdownMenuItem(
                    onClick = {
                        onColorSelected(colorItem)
                        onExpandedChange(false)
                    }
                ) {
                    Text(text = colorItem.name)
                }
            }
        }
    }
}

@Composable
fun getColor(color: PostColor): Color {
    return when (color) {
        PostColor.RED -> Color.Red.copy(alpha = 0.5f)
        PostColor.GREEN -> Color.Green.copy(alpha = 0.5f)
        PostColor.BLUE -> Color.Blue.copy(alpha = 0.5f)
        PostColor.GREY -> Color.Gray.copy(alpha = 0.5f)
        PostColor.YELLOW -> Color.Yellow.copy(alpha = 0.5f)
    }
}


@Composable
private fun DetailTopAppBar(
    onBackButtonClick: () -> Unit,
    onAddButtonClick: () -> Unit
) {
    val appBarColor = colorResource(id = R.color.violet)

    TopAppBar(
        backgroundColor = appBarColor,
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


@Composable
private fun NoteInput(onNoteEntered: (String) -> Unit) {
    val noteValue = remember { mutableStateOf("") }

    TextField(
        value = noteValue.value,
        onValueChange = { noteValue.value = it },
        modifier = Modifier
            .fillMaxSize()
            .border(1.dp, Color.LightGray),
        textStyle = TextStyle(fontSize = 16.sp),
        label = { Text("Введите заметку") },
        singleLine = false,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}





