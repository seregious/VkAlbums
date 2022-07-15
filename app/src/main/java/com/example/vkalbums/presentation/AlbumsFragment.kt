package com.example.vkalbums.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vkalbums.databinding.FragmentAlbumsBinding

class AlbumsFragment : Fragment() {

    lateinit var binding: FragmentAlbumsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater)
        return binding.root
    }

}