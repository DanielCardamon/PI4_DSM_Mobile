package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.R
import com.example.tetofinancas.databinding.FormAparelhosEletricosBinding
import com.example.tetofinancas.modelo.aparelhos.AparelhosEletricos
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.time.LocalTime
import okhttp3.Call
import okhttp3.Callback

import okhttp3.Response

class FormAparelhoEActivity : AppCompatActivity() {
    private lateinit var binding: FormAparelhosEletricosBinding
    private var clientHttp = OkHttpClient()
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormAparelhosEletricosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancelar.setOnClickListener {
            var carregar = Intent(this, ListaAparelhosEletricosActivity::class.java)
            startActivity(carregar)
        }
        binding.btnSalvar.setOnClickListener {
            val nomeAparelhoE = findViewById<EditText>(R.id.edt_nomeAparelhoE)
            val valorAparelhoE = findViewById<EditText>(R.id.edt_valorAparelhoE)
            val potenciaE = findViewById<EditText>(R.id.edt_potenciaAparelhoE)
            val tempoUsoE = findViewById<EditText>(R.id.edt_tempoUsoAparelhoE)
            //var aparelho : AparelhosEletricos
            // this.nomeAparelhoEletrico = nomeAparelhoE

        }
    }
}