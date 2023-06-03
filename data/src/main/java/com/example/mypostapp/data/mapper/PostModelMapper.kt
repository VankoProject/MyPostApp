package com.example.mypostapp.data.mapper

import com.example.mypostapp.data.database.PostDbEntity
import com.example.mypostapp.data.network.modelDto.ImagesDto
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel

class PostModelMapper {

    fun mapImagesDtoToImages(imagesDto: ImagesDto): List<ImageItem> =
        imagesDto.map { ImageItem(it.id, it.url) }

    fun mapPostModelToPostDbEntity(postModel: PostModel): PostDbEntity =
        PostDbEntity(
            description = postModel.description,
            imageUrl = postModel.imageUrl,
            color = postModel.color,
            creatingDate = postModel.date
        )

    fun mapDbEntityToPostModel(postDbEntity: PostDbEntity) = PostModel(
        id = postDbEntity.id,
        description = postDbEntity.description,
        imageUrl = postDbEntity.imageUrl,
        color = postDbEntity.color,
        date = postDbEntity.creatingDate
    )
}