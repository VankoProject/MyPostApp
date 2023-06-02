package com.example.mypostapp.data.network.modelDto

import com.google.gson.annotations.SerializedName

data class ImageItemDto(

    @SerializedName("id")
    val id: String,
    @SerializedName("download_url")
    val url: String,

)