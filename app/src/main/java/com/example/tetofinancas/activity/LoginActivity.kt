package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.LayoutLoginBinding

class LoginActivity:AppCompatActivity() {
    private lateinit var binding: LayoutLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.txtNovousuario2.setOnClickListener{
           val carregar = Intent(this, CadastroActivity::class.java)
           startActivity(carregar)
       }
        binding.btnLogar.setOnClickListener{
            val carregar = Intent(this, HomeActivity::class.java)
            startActivity(carregar)
        }
    }
}