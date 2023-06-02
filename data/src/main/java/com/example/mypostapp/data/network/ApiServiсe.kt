package com.example.mypostapp.data.network

import com.example.mypostapp.data.network.modelDto.ImagesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET ("/v2/list")
    suspend fun getListImages(
        @Query(QUERY_PARAM_PAGE) page: Int,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 50
    ): Response<ImagesDto>


    companion object {
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_LIMIT = "limit"
    }

}