package com.example.vkalbums.data

import com.example.vkalbums.domain.AlbumsData
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsInterface {
    @GET
    suspend fun getAlbumsByToken(@Path("request") request: String): AlbumsData
}