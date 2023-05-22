package com.example.mypostapp.domain

import com.example.mypostapp.domain.model.PostModel

interface PostRepository {

    fun getAllPosts(): List<PostModel>

    suspend fun deletePost(postModel: PostModel)

    suspend fun addNewPost(postModel: PostModel)
}