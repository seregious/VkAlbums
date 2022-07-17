package com.example.vkalbums.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumsDownload {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.vk.com/method/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getAlbums = retrofit.create(AlbumsInterface::class.java)
}