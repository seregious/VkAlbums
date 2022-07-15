package com.example.vkalbums.data

import com.example.vkalbums.domain.User
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumsInterface {
    @GET("{token}")
    suspend fun getPhotosByToken(@Path("token") token: String): List<User>
}