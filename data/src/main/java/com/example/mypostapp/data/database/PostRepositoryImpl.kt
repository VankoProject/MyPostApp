package com.example.mypostapp.data.database

import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.PostModel

class PostRepositoryImpl: PostRepository {

    override fun getAllPosts(): List<PostModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(postModel: PostModel) {
        TODO("Not yet implemented")
    }

    override suspend fun addNewPost(postModel: PostModel) {
        TODO("Not yet implemented")
    }
}