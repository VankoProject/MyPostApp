package com.example.mypostapp.presentation.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypostapp.data.PostRepositoryImpl
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel
import com.example.mypostapp.domain.usecases.AddNewPostUseCase
import com.example.mypostapp.domain.usecases.GetListImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PostRepositoryImpl(application = application)
    private val getListImages = GetListImages(repository)
    private val addNewPostUseCase = AddNewPostUseCase(repository)
    private val _imageItems = MutableStateFlow<List<ImageItem>>(emptyList())
    val imageItems: StateFlow<List<ImageItem>> get() = _imageItems

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            try {
                val images = getListImages.invoke()
                _imageItems.value = images
            } catch (e: Exception) {
                Log.d("ViewModel", "Error")
            }
        }
    }

    fun savePostToDatabase(
        description: String,
        imageUrl: String,
        color: String,
    ) {
        viewModelScope.launch {
            addNewPostUseCase.invoke(
                postModel = PostModel(
                    id = 0,
                    description = description,
                    imageUrl = imageUrl,
                    color = color,
                    date = System.currentTimeMillis()
                ))
        }
    }
}