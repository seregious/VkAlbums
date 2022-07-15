package com.example.vkalbums.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vkalbums.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupFragment()
    }


    private fun setupFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, AuthFragment())
            .commit()
    }

}