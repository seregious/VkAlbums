package com.example.vkalbums.data


object AlbumsRequest{
    fun makeRequest(id: String, token: String): String {
        val method: String = "photos.getAlbums?"
        val userId: String = "owner_id="
        val covers: String = "&need_covers=1"
        val userToken: String = "&access_token="
        return method + id + userId + covers + userToken + token + Constants.API
    }
}
