package com.example.vkalbums.domain

data class Albums(
    val response: Response
)

data class Response (
    val items: ArrayList<Album>
        )

data class Album(
    val id: Int,
    val title: String,
    val thumb_src: String
)
