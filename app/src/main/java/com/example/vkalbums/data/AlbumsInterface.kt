package com.example.vkalbums.data

import com.example.vkalbums.domain.AlbumsData
import retrofit2.http.GET

interface AlbumsInterface {
    @GET("photos.getAlbums?owner_id=85552697&need_covers=1&access_token=vk1.a.-RUPxBnKFdHXhurk2JAkkBG8w2SHQJI-k07isUjO4Fu6VqbavPdsx_3bmyp07b4zezc_ptbLOqOs6IUN6-HVYm1OVqC9qOxN9I46jCG2A28x4sxoD3oC3jnovMF8TQUYrZE0eVEsd9ZErfGUCKJNJYyc6hhUP72-bSmxeY42JvJjZK3jxt22fh9CblLLkV91&v=5.131")
    suspend fun getAlbumsByToken(): AlbumsData
}