package com.example.mypostapp.data

import android.app.Application
import com.example.mypostapp.data.database.PostDataBase
import com.example.mypostapp.data.mapper.PostModelMapper
import com.example.mypostapp.data.network.ApiFactory
import com.example.mypostapp.domain.PostRepository
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel

class PostRepositoryImpl(
    application: Application
) : PostRepository {

    private val apiService = ApiFactory.apiService
    private val mapper = PostModelMapper()
    private val postDao = PostDataBase.getInstance(application).getPostDao()


    override fun getAllPosts(): List<PostModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(postModel: PostModel) {
        TODO("Not yet implemented")
    }

    override suspend fun addNewPost(postModel: PostModel) {
        val postDbModel = mapper.mapPostModelToPostDbEntity(postModel)
        postDao.addNewPostToDb(postDbModel)
    }

    override suspend fun getImagesData(): List<ImageItem> {
        val response = apiService.getListImages(page = 2, limit = 50)
        if (response.isSuccessful) {
            val imagesDto = response.body()
            if (imagesDto != null) {
                return mapper.mapImagesDtoToImages(imagesDto)
            }
        }
        throw IllegalStateException("Failed to get images data")
    }
}