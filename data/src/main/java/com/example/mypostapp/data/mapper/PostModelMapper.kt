package com.example.mypostapp.data.mapper

import com.example.mypostapp.data.network.modelDto.ImagesDto
import com.example.mypostapp.domain.model.ImageItem

class PostModelMapper {

    fun mapImagesDtoToImages(imagesDto: ImagesDto): List<ImageItem> =
        imagesDto.map { ImageItem(it.id, it.url)}



}