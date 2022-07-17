package com.example.vkalbums.data

import com.example.vkalbums.data.network.DownloadSingleton
import com.example.vkalbums.domain.Photo

object PhotosRequest {
    suspend fun get(id: String, idAlbum: String, token: String): List<Photo> {
        return DownloadSingleton.getPhotos.getPhotosByToken(id, idAlbum, token).response.items
    }
}