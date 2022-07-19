package com.example.vkalbums.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vkalbums.R
import com.example.vkalbums.databinding.FragmentPhotosBinding
import com.example.vkalbums.domain.Photo
import com.example.vkalbums.presentation.PhotosAdapter
import com.example.vkalbums.presentation.ViewModel

class PhotosFragment : Fragment(), PhotosAdapter.Listener {

    private lateinit var binding: FragmentPhotosBinding
    private val viewModel: ViewModel by activityViewModels()
    private lateinit var photosAdapter: PhotosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, AlbumsFragment()).commit()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPhotos()
        setTitle()


    }

    private fun setupPhotos() {
        viewModel.getPhotos()
        binding.photosRecycler.layoutManager = GridLayoutManager(context, 2)
        photosAdapter = PhotosAdapter(this)
        binding.photosRecycler.adapter = photosAdapter
        viewModel.photosList.observe(viewLifecycleOwner) {
            photosAdapter.photosList = it
        }
    }

    override fun onClick(photo: Photo) {
        setupFragment()
        viewModel.currentPhoto.value = photo
    }

    private fun setupFragment() {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, DetailedFragment()).commit()
    }

    private fun setTitle() {
        val text = viewModel.currentAlbum.value?.title
        (activity as AppCompatActivity).supportActionBar?.title = text
    }

}