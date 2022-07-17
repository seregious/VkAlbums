package com.example.vkalbums.data

object PhotosRequest {
    fun makeRequest(userId: String, token: String, albumId: String): String {
        val ownerId: String = "owner_id"
        val albumId: String = "&album_id"
        val userToken: String = "&access_token="
        return ownerId + userId + albumId + albumId + userToken + token + Constants.API
    }
}