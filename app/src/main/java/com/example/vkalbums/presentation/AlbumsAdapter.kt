package com.example.vkalbums.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkalbums.R
import com.example.vkalbums.databinding.AlbumCardBinding
import com.example.vkalbums.domain.Album
import com.squareup.picasso.Picasso

class AlbumsAdapter(
    private val listener: Listener
): RecyclerView.Adapter<AlbumsAdapter.RepHolder>() {

    var albumsList: List<Album> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class RepHolder(view: View): RecyclerView.ViewHolder(view) {
        private var binding = AlbumCardBinding.bind(view)

        fun bind(album: Album, listener: Listener) = with(binding){
            Picasso.get().load(album.thumb_src).into(albumCover)
            albumName.text = album.title
            itemView.setOnClickListener {
                listener.onClick(album)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album_card, parent, false)
        return RepHolder(view)
    }

    override fun onBindViewHolder(holder: RepHolder, position: Int) {
        holder.bind(albumsList[position], listener)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    interface Listener {
        fun onClick(album: Album)
    }
}