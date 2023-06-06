package com.example.mypostapp.domain

import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel

interface PostRepository {

    suspend fun getAllPosts(): List<PostModel>

    suspend fun deletePost(postModel: PostModel)

    suspend fun addNewPost(postModel: PostModel)

    suspend fun getImagesData(): List<ImageItem>
}