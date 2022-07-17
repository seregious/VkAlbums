package com.example.vkalbums.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vkalbums.databinding.FragmentAlbumsBinding
import com.example.vkalbums.domain.Album

class AlbumsFragment : Fragment(), AlbumsAdapter.Listener {

    private lateinit var binding: FragmentAlbumsBinding
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAlbums()
    }

    private fun setupAlbums() {
        viewModel.getAlbums()
        binding.albumsRecycler.layoutManager = GridLayoutManager(context, 2)
        albumsAdapter = AlbumsAdapter(this)
        binding.albumsRecycler.adapter = albumsAdapter
        viewModel.albumsList.observe(viewLifecycleOwner) {
            albumsAdapter.albumsList = it
        }
    }

    override fun onClick(album: Album) {

    }

}