package com.example.vkalbums.presentation.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vkalbums.databinding.FragmentAlbumsBinding
import com.example.vkalbums.domain.Album
import com.example.vkalbums.presentation.AlbumsAdapter
import com.example.vkalbums.presentation.ViewModel


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
        setTitle()
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
        setupFragment()
        viewModel.currentAlbum.value = album
    }

    private fun setupFragment() {
        parentFragmentManager.beginTransaction().replace(com.example.vkalbums.R.id.fragmentContainer, PhotosFragment()).commit()
    }

    private fun setTitle() {
        val text = getString(com.example.vkalbums.R.string.albums)
        (activity as AppCompatActivity).supportActionBar?.title = text
    }
}