package com.example.vkalbums.presentation

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.vkalbums.R
import com.example.vkalbums.presentation.fragments.AlbumsFragment
import com.example.vkalbums.presentation.fragments.FirstFragment


class MainActivity : AppCompatActivity() {

    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkToken()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        clear()
        setupFirstFragment()
        showLogOutToast()
        return true
    }


    private fun setupAuthFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, FirstFragment())
            .commit()
    }

    private fun setupAlbumsFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, AlbumsFragment())
            .commit()
    }

    private fun setupFirstFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, FirstFragment())
            .commit()
    }

    private fun checkToken() {
        viewModel.getAlbums()
        when (viewModel.isTokenValid.value) {
            true -> setupAlbumsFragment()
            else -> setupAuthFragment()
        }
    }

    private fun clear() {
        val shared = getSharedPreferences("user", Context.MODE_PRIVATE)
        val edit = shared.edit()
        edit.clear()
        viewModel.currentUser.value = null
    }

    private fun showLogOutToast() {
        Toast.makeText(this, R.string.loginToast, Toast.LENGTH_LONG).show()
    }

}