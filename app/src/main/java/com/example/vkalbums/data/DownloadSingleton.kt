package com.example.vkalbums.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DownloadSingleton {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getAlbums: AlbumsInterface = retrofit.create(AlbumsInterface::class.java)
    var getPhotos = retrofit.create(PhotosInterface::class.java)
}