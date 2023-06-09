package com.example.mypostapp.presentation.home

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypostapp.data.PostRepositoryImpl
import com.example.mypostapp.domain.model.PostModel
import com.example.mypostapp.domain.usecases.GetAllPostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    private val repository = PostRepositoryImpl(application = application)
    private val getAllPostsUseCase = GetAllPostsUseCase(repository)


    private val _postModels = MutableStateFlow<List<PostModel>>(emptyList())
    val postModels: StateFlow<List<PostModel>> get() = _postModels

    fun getAllPosts() {
        viewModelScope.launch {
            try {
                val listPosts = getAllPostsUseCase.invoke()
                _postModels.value = listPosts
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при получении списка записок", e)
            }
        }
    }
}