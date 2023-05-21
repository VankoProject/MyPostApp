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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mypostapp.R
import com.example.mypostapp.presentation.model.SetColor


class DetailFragment : Fragment() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                var selectedColor by remember { mutableStateOf(SetColor.RED) }
                var expandedState by remember { mutableStateOf(false) }
                val colors = SetColor.values()
                var noteText by remember { mutableStateOf("") }

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
                    Column(
                        modifier = Modifier.padding(16.dp)
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
                        Text(
                            text = "Select background color for your post",
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = FontFamily.Default.apply {
                                R.font.roboto_light
                            },
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.padding(bottom = 8.dp))
                        SelectColorForPost(
                            selectedColor = selectedColor,
                            onColorSelected = { color -> selectedColor = color },
                            expanded = expandedState,
                            onExpandedChange = { expandedState = it },
                            colors = colors,
                        )
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
private fun SelectColorForPost(
    selectedColor: SetColor?,
    onColorSelected: (SetColor) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    colors: Array<SetColor>,
) {
    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Box {
        OutlinedTextField(
            value = TextFieldValue(text = selectedColor?.name ?: "Select color"),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = getColor(color = selectedColor!!)),
            trailingIcon = {
                Icon(icon, null, Modifier.clickable {
                    onExpandedChange(!expanded)
                })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            offset = DpOffset(0.dp, 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                )
        ) {
            colors.forEach { colorItem ->
                DropdownMenuItem(
                    onClick = {
                        onColorSelected(colorItem)
                        onExpandedChange(false)
                    },
                    modifier = Modifier
                        .background(color = getColor(color = colorItem))
                ) {
                    Text(text = colorItem.name)
                }
            }
        }
    }
}

@Composable
fun getColor(color: SetColor): Color {
    return when (color) {
        SetColor.RED -> Color.Red.copy(alpha = 0.5f)
        SetColor.GREEN -> Color.Green.copy(alpha = 0.5f)
        SetColor.BLUE -> Color.Blue.copy(alpha = 0.5f)
        SetColor.GREY -> Color.Gray.copy(alpha = 0.5f)
        SetColor.YELLOW -> Color.Yellow.copy(alpha = 0.5f)
    }
}

@Composable
private fun NoteInput(onNoteEntered: (String) -> Unit) {
    val noteValue = remember { mutableStateOf("") }

    TextField(
        value = noteValue.value,
        onValueChange = { newValue -> noteValue.value = newValue },
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
            ),
        textStyle = TextStyle(color = Color.Black),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
    )
}






