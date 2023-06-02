package com.example.mypostapp.data

import com.example.mypostapp.data.mapper.PostModelMapper
import com.example.mypostapp.data.network.ApiService
import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel

class PostRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: PostModelMapper
) : PostRepository {

    override fun getAllPosts(): List<PostModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(postModel: PostModel) {
        TODO("Not yet implemented")
    }

    override suspend fun addNewPost(postModel: PostModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getImagesData(): List<ImageItem> {
        val response = apiService.getListImages(page = 1, limit = 50)
        if (response.isSuccessful) {
            val imagesDto = response.body()
            if (imagesDto != null) {
                return mapper.mapImagesDtoToImages(imagesDto)
            }
        }
        throw IllegalStateException("Failed to get images data")
    }
}