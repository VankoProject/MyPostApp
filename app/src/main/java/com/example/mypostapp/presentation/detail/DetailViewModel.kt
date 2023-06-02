package com.example.mypostapp.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypostapp.data.PostRepositoryImpl
import com.example.mypostapp.data.mapper.PostModelMapper
import com.example.mypostapp.data.network.ApiFactory
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.usecases.GetListImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel() {


    private val apiService = ApiFactory.apiService
    private val mapper = PostModelMapper()
    private val repository = PostRepositoryImpl(apiService,mapper)
    private val getListImages = GetListImages(repository)
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

}