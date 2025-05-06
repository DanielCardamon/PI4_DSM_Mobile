package com.example.tetofinancas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.LayoutLoginBinding

class MainActivity:AppCompatActivity() {
    private lateinit var binding: LayoutLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}