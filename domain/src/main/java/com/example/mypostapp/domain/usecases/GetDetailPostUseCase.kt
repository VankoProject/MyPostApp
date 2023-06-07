package com.example.mypostapp.domain.usecases

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.PostModel

class GetDetailPostUseCase(private val repository: PostRepository) {

    suspend operator fun invoke(postId: Long): PostModel {
        return repository.getDetailPost(postId)
    }

}