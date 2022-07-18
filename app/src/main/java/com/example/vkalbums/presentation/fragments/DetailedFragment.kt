package com.example.vkalbums.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.vkalbums.databinding.FragmentDetailedBinding
import com.example.vkalbums.presentation.ViewModel
import com.squareup.picasso.Picasso
import java.time.Instant
import java.time.ZoneId


class DetailedFragment : Fragment() {

    private lateinit var binding: FragmentDetailedBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle()
        setImage()
        setupButton()
    }

    private fun setTitle() {
        val text = getDateTime()
        (activity as AppCompatActivity).supportActionBar?.title = text
    }

    private fun setImage() {
        val image = viewModel.currentPhoto.value?.sizes?.last()?.url
        Picasso.get().load(image).into(binding.imageView)
    }

    private fun getDateTime(): String {
        val date = viewModel.currentPhoto.value?.date
        var dt = ""
        if (date != null) {
            dt = Instant.ofEpochSecond(date)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().toString()
        }
        return dt
    }

    private fun setupButton() {
        binding.shareButton.setOnClickListener {

        }
    }
}