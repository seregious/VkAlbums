package com.example.vkalbums.data.network

import com.example.vkalbums.domain.AlbumsData
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumsInterface {
    @GET("photos.getAlbums")
    suspend fun getAlbumsByToken(
        @Query("owner_id") id: String,
        @Query("need_covers") covers: String = "1",
        @Query("photo_sizes") photoSizes: String = "1",
        @Query("access_token") token: String,
        @Query("v") api: String = "5.131"
    ): AlbumsData
}