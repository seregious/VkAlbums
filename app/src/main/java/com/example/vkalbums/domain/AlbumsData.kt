package com.example.vkalbums.domain

data class AlbumsData(
    val response: ResponseAlbums
)

data class ResponseAlbums (
    val items: List<Album>
        )

data class Album(
    val id: Long,
    val title: String,
    val thumb_src: String
)
