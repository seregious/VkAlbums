package com.example.vkalbums.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vkalbums.R
import com.example.vkalbums.databinding.PhotoCardBinding
import com.example.vkalbums.domain.Photo
import com.squareup.picasso.Picasso

class PhotosAdapter(
    private val listener: Listener
): RecyclerView.Adapter<PhotosAdapter.PhotoHolder>() {

    var photosList: List<Photo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class PhotoHolder(view: View): RecyclerView.ViewHolder(view) {
        private var binding = PhotoCardBinding.bind(view)

        fun bind(photo: Photo, listener: Listener) = with(binding){
            Picasso.get().load(photo.sizes.last().url).into(photoView)
            itemView.setOnClickListener {
                listener.onClick(photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_card, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(photosList[position], listener)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    interface Listener {
        fun onClick(photo: Photo)
    }
}