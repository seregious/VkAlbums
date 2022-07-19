package com.example.vkalbums.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.vkalbums.R
import com.example.vkalbums.databinding.FragmentFirstBinding
import com.example.vkalbums.domain.User
import com.example.vkalbums.presentation.ViewModel


class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadUser()
        checkUser()
        setButton()
    }

    private fun checkUser() {
        if (viewModel.currentUser.value == null) {
            showLoginToast()
        } else {
            viewModel.getAlbums()
            if (viewModel.isTokenValid.value == true) {
                setupAlbumsFragment()
            } else {
                setupAuthFragment()
                //showErrorToast()
            }
        }
    }

    private fun loadUser() {
        if (activity != null) {
            val shared = activity!!.getSharedPreferences("user", Context.MODE_PRIVATE)
            val token = shared.getString("token", null)
            val id = shared.getString("id", null)
            if (id != null && token != null) {
                val user = User(token, id)
                viewModel.currentUser.value = user
            }
        }
    }

    private fun setupAuthFragment() {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, AuthFragment()).commit()
    }

    private fun setupAlbumsFragment() {
        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer, AlbumsFragment()).commit()
    }

//    private fun showErrorToast() {
//        Toast.makeText(this@FirstFragment.requireActivity(), R.string.errorToken, Toast.LENGTH_LONG).show()
//    }

    private fun showLoginToast() {
        Toast.makeText(this@FirstFragment.requireActivity(), R.string.loginToast, Toast.LENGTH_LONG).show()
    }

    private fun setButton() {
        binding.loginButton.setOnClickListener {
            setupAuthFragment()
        }
    }

}