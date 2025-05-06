package com.example.tetofinancas

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.LayoutHomeBinding

class HomeActivity:AppCompatActivity() {
    private lateinit var binding: LayoutHomeBinding
    override fun onCreate(bundle:Bundle?) {
        super.onCreate(bundle)
        binding=LayoutHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}