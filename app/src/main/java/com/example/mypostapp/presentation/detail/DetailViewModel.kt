package com.example.mypostapp.presentation.detail

import android.app.Application
import android.util.Log
import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mypostapp.data.PostRepositoryImpl
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel
import com.example.mypostapp.domain.usecases.AddNewPostUseCase
import com.example.mypostapp.domain.usecases.GetDetailPostUseCase
import com.example.mypostapp.domain.usecases.GetListImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PostRepositoryImpl(application = application)
    private val getListImages = GetListImages(repository)
    private val addNewPostUseCase = AddNewPostUseCase(repository)
    private val getDetailPostUseCase = GetDetailPostUseCase(repository)
    private val _imageItems = MutableStateFlow<List<ImageItem>>(emptyList())
    val imageItems: StateFlow<List<ImageItem>> get() = _imageItems

    private val _postState = MutableStateFlow<PostModel?>(null)
    val postState: StateFlow<PostModel?> = _postState

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            try {
                val images = getListImages()
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
            addNewPostUseCase(
                postModel = PostModel(
                    id = 0,
                    description = description,
                    imageUrl = imageUrl,
                    color = color,
                    date = System.currentTimeMillis()
                )
            )
        }
    }

    fun getDetailPostFromDb(postId: Long) {
        viewModelScope.launch (Dispatchers.IO) {
            val postModel = getDetailPostUseCase(postId)
            _postState.value = postModel
            }
        }
}