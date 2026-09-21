package com.ute.btl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.btl.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvWelcome.text = "Chào mừng bạn đến với ViewBinding!"
    }
}