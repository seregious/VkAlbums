package com.example.vkalbums.domain

data class AlbumsData(
    val response: Response
)

data class Response (
    val items: List<Album>
        )

data class Album(
    val id: Long,
    val title: String,
    val thumb_src: String
)
