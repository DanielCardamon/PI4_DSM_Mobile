package com.example.tetofinancas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.LayoutCadastroBinding
import com.example.tetofinancas.databinding.LayoutLoginBinding

class CadastroActivity:AppCompatActivity() {
    private lateinit var binding: LayoutCadastroBinding
    override fun onCreate(bundle: Bundle?){
        super.onCreate(bundle)
        binding = LayoutCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtEntre.setOnClickListener{
            val carregar = Intent(this, LoginActivity::class.java)
            startActivity(carregar)
        }
    }

}