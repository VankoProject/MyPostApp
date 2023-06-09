package com.example.mypostapp.domain.usecases

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.ImageItem

class GetListImages(private val postRepository: PostRepository) {

    suspend operator fun invoke (): List<ImageItem> {
        return postRepository.getImagesData()
    }

}