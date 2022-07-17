package com.example.vkalbums.domain

data class PhotosData(
    val response: ResponsePhotos
)

data class ResponsePhotos (
    val items: List<Photo>
        )

data class Photo (
    val date: Long,
    val sizes: List<PhotoSize>
        )

data class PhotoSize (
    val url: String
        )