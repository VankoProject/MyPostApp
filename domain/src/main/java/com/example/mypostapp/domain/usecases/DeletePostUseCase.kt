package com.example.mypostapp.domain.usecases

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.PostModel

class DeletePostUseCase (private val repository: PostRepository) {

    suspend operator fun invoke (postModel: PostModel) {
        repository.deletePost(postModel)
    }
}