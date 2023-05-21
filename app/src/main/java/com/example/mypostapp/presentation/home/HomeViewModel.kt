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
                    description = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.",
                    image = R.drawable.image_note,
                    color = Color(255,
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()),
                    date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                )
            )
        }
    }

    private val _postModels = MutableLiveData<List<PostModel>>(initialPostsList)
    val postModels: LiveData<List<PostModel>> = _postModels
}