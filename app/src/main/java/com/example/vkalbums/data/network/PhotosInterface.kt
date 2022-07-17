package com.example.vkalbums.data.network

import com.example.vkalbums.domain.PhotosData
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosInterface {
    @GET("photos.get")
    suspend fun getPhotosByToken(
        @Query("owner_id") id: String,
        @Query("album_id") idAlbum: String,
        @Query("access_token") token: String,
        @Query("v") api: String = "5.131"
    ): PhotosData
}