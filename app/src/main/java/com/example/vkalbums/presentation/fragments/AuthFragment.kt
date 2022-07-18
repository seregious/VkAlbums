package com.example.vkalbums.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.vkalbums.R
import com.example.vkalbums.data.Constants
import com.example.vkalbums.databinding.FragmentAuthBinding
import com.example.vkalbums.domain.User
import com.example.vkalbums.presentation.ViewModel


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
        openUrl()
    }

    private fun setupWebView() = with(binding) {
        webView.webViewClient = WebViewClient()
        webView.loadUrl(Constants.AUTH_URL)
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)
    }

    private fun openUrl() = with(binding){
        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    webView.loadUrl(url)
                    setupUser(url)
                }
                return true
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    private fun chekSignIn() {
        if (loadUser() != null) {
            setupFragment()
            Log.d("url", "loaded")
        }
    }

    private fun setupUser(url: String) {
        with (url) {
            when {
                contains("https://oauth.vk.com/blank.html#access_token=vk1") -> newLogin(url)
                else -> Log.d("url", "url denied")
            }
        }
    }

    private fun newLogin(url: String) {
        getIdTokenFromUrl(url)
        chekSignIn()
        saveUser()
        setupFragment()
    }

    private fun getIdTokenFromUrl(url: String) {
        if (url.length == 277) {
            val token = url.subSequence(45, 243).toString()
            val id = url.subSequence(269, 277).toString()
            val user = User(token, id)
            viewModel.currentUser.value = user
        }
    }

    private fun setupFragment() {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, AlbumsFragment()).commit()
    }

    private fun saveUser() {
        if (activity != null) {
            val shared = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
            val edit = shared.edit()
            edit.putString("token", viewModel.currentUser.value?.token)
            edit.putString("id", viewModel.currentUser.value?.id)
            edit.commit()
        }
    }

    private fun loadUser(): String? {
        if (activity != null) {
            val shared = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
            val token = shared.getString("token", null)
            val id = shared.getString("id", null)
            if (id != null && token != null) {
                val user = User(token, id)
                viewModel.currentUser.value = user
            }
        }
        return viewModel.currentUser.value?.token
    }

//    private fun clear() {
//        val shared = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
//        val edit = shared.edit()
//        edit.clear()
//    }

}