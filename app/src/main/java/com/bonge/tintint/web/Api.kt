package com.bonge.tintint.web

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("photos")
    suspend fun getPhotos(
        @Query("albumId") albumId: Int
    ): List<PhotosResponse>

}