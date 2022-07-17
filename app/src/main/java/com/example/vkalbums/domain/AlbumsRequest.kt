package com.example.vkalbums.domain

import com.example.vkalbums.data.Constants

data class AlbumsRequest(
    val url: String = Constants.BASE_URL,
    val method: String = "photos.getAlbums?",
    val id: String = "owner_id=",
    val user_id: String = "",
    val covers: String = "&need_covers=1",
    val token: String = "&access_token=",
    val user_token : String = "",
    val api: String = "&v=5.131"
) {
    fun makeRequest(): String {
        return url + method + id + user_id + covers + token + user_token + api
    }
}
