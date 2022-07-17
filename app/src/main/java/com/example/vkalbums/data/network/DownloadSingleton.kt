package com.example.vkalbums.data.network

import com.example.vkalbums.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DownloadSingleton {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var getAlbums: AlbumsInterface = retrofit.create(AlbumsInterface::class.java)
    var getPhotos: PhotosInterface = retrofit.create(PhotosInterface::class.java)
}