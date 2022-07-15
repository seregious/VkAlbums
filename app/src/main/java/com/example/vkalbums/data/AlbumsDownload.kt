package com.example.vkalbums.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumsDownload {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getPhotos = retrofit.create(AlbumsInterface::class.java)
}