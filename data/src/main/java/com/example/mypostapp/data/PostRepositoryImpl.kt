package com.example.mypostapp.data

import android.app.Application
import com.example.mypostapp.data.database.PostDataBase
import com.example.mypostapp.data.mapper.PostModelMapper
import com.example.mypostapp.data.mapper.PostModelMapper.Companion.toPostDbEntity
import com.example.mypostapp.data.mapper.PostModelMapper.Companion.toPostModel
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


    override suspend fun getAllPosts(): List<PostModel> {
        return postDao.getAllPosts()
            .map { it.toPostModel() }

    }

    override suspend fun getDetailPost(postId: Long): PostModel {
        return postDao.getPost(postId).toPostModel()
    }

    override suspend fun deletePost(postModel: PostModel) {
        postDao.deletePostFromDb(postModel.toPostDbEntity())
    }

    override suspend fun addNewPost(postModel: PostModel) {
        postDao.addNewPostToDb(postModel.toPostDbEntity())
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