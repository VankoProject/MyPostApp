package com.example.mypostapp.domain.usecases

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.PostModel

class AddNewPostUseCase(private val repository: PostRepository) {

    suspend operator fun invoke (postModel: PostModel) {
        val postWithDate = postModel.copy(date = System.currentTimeMillis())
        repository.addNewPost(postWithDate)
    }
}