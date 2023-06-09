package com.example.mypostapp.data.mapper

import com.example.mypostapp.data.database.PostDbEntity
import com.example.mypostapp.data.network.modelDto.ImagesDto
import com.example.mypostapp.domain.model.ImageItem
import com.example.mypostapp.domain.model.PostModel

class PostModelMapper {

    fun mapImagesDtoToImages(imagesDto: ImagesDto): List<ImageItem> =
        imagesDto.map { ImageItem(it.id, it.url) }


    companion object {
        fun PostDbEntity.toPostModel(): PostModel = PostModel(
            id = id,
            description = description,
            imageUrl = imageUrl,
            color = color,
            date = creatingDate
        )

        fun PostModel.toPostDbEntity(): PostDbEntity = PostDbEntity(
            description =description,
            imageUrl = imageUrl,
            color = color,
            creatingDate = date
        )
    }
}