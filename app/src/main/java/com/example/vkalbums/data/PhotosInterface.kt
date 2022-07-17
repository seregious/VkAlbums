package com.example.vkalbums.data

import com.example.vkalbums.domain.PhotosData
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosInterface {
    @GET
    suspend fun getPhotos(@Path("request") request: String): PhotosData
}