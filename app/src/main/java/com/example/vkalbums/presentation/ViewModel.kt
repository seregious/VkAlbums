package com.example.vkalbums.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val currentPhoto: MutableLiveData<Photo> by lazy {
        MutableLiveData<Photo>()
    }

    var albumsList = MutableLiveData<List<Album>>()
    var photosList = MutableLiveData<List<Photo>>()

    fun getAlbums() {
        var list: List<Album>
        if (currentUser.value != null) {
            val userId = currentUser.value!!.id
            val userToken = currentUser.value!!.token
            viewModelScope.launch {
                try {
                    list = AlbumsRequest.get(userId,userToken)
                    albumsList.postValue(list)
                } catch (e: Exception) {
                    Log.d("url", "fail download albums")
                }
            }
        }
    }

    fun getPhotos() {
        var list: List<Photo>
        if (currentUser.value != null) {
            val userId = currentUser.value!!.id
            val userToken = currentUser.value!!.token
            val album = currentAlbum.value!!.id.toString()
            viewModelScope.launch {
                try {
                    list = PhotosRequest.get(userId, album, userToken)
                    photosList.postValue(list)
                } catch (e: Exception) {
                    Log.d("url", "fail download photos")
                }
            }
        }
    }
}