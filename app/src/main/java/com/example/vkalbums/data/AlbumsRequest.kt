package com.example.vkalbums.data

import com.example.vkalbums.data.network.DownloadSingleton
import com.example.vkalbums.domain.Album


object AlbumsRequest{
    suspend fun get(id: String, token: String): List<Album> {
        return DownloadSingleton.getAlbums.getAlbumsByToken(id = id, token = token).response.items
    }
}
