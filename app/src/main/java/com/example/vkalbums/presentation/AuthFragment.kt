package com.example.vkalbums.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.vkalbums.R
import com.example.vkalbums.databinding.FragmentAuthBinding


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    private fun setupWebView() = with(binding) {
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com/?client=safari")
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }

}