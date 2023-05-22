package com.example.mypostapp.domain.usecases

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.PostModel

class GetAllPostsUseCase(private val repository: PostRepository) {

    operator fun invoke (): List<PostModel> {
        return repository.getAllPosts()
    }

}