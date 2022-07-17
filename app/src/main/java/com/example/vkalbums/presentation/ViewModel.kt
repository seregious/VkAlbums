package com.example.vkalbums.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkalbums.data.AlbumsDownload
import com.example.vkalbums.domain.Album
import com.example.vkalbums.domain.AlbumsData
import com.example.vkalbums.domain.AlbumsRequest
import com.example.vkalbums.domain.User
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModel: ViewModel() {

    val currentUser: MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }

    var albumsList = MutableLiveData<List<Album>>()

    fun getAlbums() {
        var list: List<Album>
        val request = makeUrlRequest()
        viewModelScope.launch {
            try {
                list = AlbumsDownload.getAlbums.getAlbumsByToken().response.items
                albumsList.postValue(list)
                Log.d("url", "viewModel" + list.size.toString())
            } catch(e: Exception) {
                Log.d("url", "fail download")
            }
        }
    }

    private fun makeUrlRequest(): String {
        var request = AlbumsRequest()
        if (currentUser.value != null) {
            val userId = currentUser.value!!.id
            val userToken = currentUser.value!!.token
            request = AlbumsRequest(user_id = userId, user_token = userToken)
        }
        return request.makeRequest()
    }
}