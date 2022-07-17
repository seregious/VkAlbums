package com.example.vkalbums.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkalbums.data.DownloadSingleton
import com.example.vkalbums.domain.Album
import com.example.vkalbums.data.AlbumsRequest
import com.example.vkalbums.data.PhotosRequest
import com.example.vkalbums.domain.Photo
import com.example.vkalbums.domain.User
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModel: ViewModel() {

    val currentUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    val currentAlbum: MutableLiveData<Album> by lazy {
        MutableLiveData<Album>()
    }

    var albumsList = MutableLiveData<List<Album>>()
    var photosList = MutableLiveData<List<Photo>>()

    fun getAlbums() {
        var list: List<Album>
        val request = makeAlbumsRequest()
        viewModelScope.launch {
            try {
                list = DownloadSingleton.getAlbums.getAlbumsByToken(request).response.items
                albumsList.postValue(list)
            } catch(e: Exception) {
                Log.d("url", "fail download albums")
            }
        }
    }

    fun getPhotos() {
        var list: List<Photo>
        val request = makePhotoRequest()
        viewModelScope.launch {
            try {
                list = DownloadSingleton.getPhotos.getPhotos(request).response.items
                photosList.postValue(list)
            } catch(e: Exception) {
                Log.d("url", "fail download photos")
            }
        }
    }

    private fun makeAlbumsRequest(): String {
        var request = ""
        if (currentUser.value != null) {
            val userId = currentUser.value!!.id
            val userToken = currentUser.value!!.token
            request = AlbumsRequest.makeRequest(userId, userToken)
        }
        return request
    }

    private fun makePhotoRequest(): String {
        var request = ""
        if (currentUser.value != null) {
            val userId = currentUser.value!!.id
            val userToken = currentUser.value!!.token
            val album = currentAlbum.value!!.id.toString()
            request = PhotosRequest.makeRequest(userId,userToken,album)
        }
        return request
    }
}