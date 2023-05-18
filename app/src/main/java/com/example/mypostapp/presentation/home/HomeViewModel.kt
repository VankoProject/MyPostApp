package com.example.mypostapp.presentation.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mypostapp.R
import com.example.mypostapp.presentation.model.PostModel
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel: ViewModel() {

    private val initialPostsList = mutableListOf<PostModel>().apply {
        repeat(50) {
            add(
                PostModel(
                    id = it,
                    description = it.toString(),
                    image = R.drawable.image_note,
                    color = Color(255,
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()),
                    date = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                )
            )
        }
    }

    private val _postModels = MutableLiveData<List<PostModel>>(initialPostsList)
    val postModels: LiveData<List<PostModel>> = _postModels
}