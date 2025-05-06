package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.R
import com.example.tetofinancas.databinding.FormEnderecosBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class FormEnderecosActivity:AppCompatActivity(){
    private var clientHttp = OkHttpClient()
    private lateinit var binding: FormEnderecosBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormEnderecosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancelarE.setOnClickListener {
            var carregar = Intent(this, HomeActivity::class.java)
            startActivity(carregar)
        }
        binding.btnSalvarE.setOnClickListener {
            val cep = findViewById<EditText>(R.id.edt_cep)
            val cidade = findViewById<EditText>(R.id.edt_cidade)
            val bairro = findViewById<EditText>(R.id.edt_bairro)
            val rua = findViewById<EditText>(R.id.edt_rua)
            val numero = findViewById<EditText>(R.id.edt_numero)
            val complemento = findViewById<EditText>(R.id.edt_Complemento)



            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                  {
 
  "cep": ${cep.text.toString().toDouble()},
  "cidade": ${cidade.text.toString().toDouble()},  
  "bairro": ${bairro.text}
  "rua": ${rua.text}
  "numero": ${numero.text}
  "complemeto": ${complemento.text}
}
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/enderecos.json")
                .build()
            val response = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("Enderecos", e?.message.toString())
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val localBody = response?.body()
                    localBody?.charStream()?.buffered()?.lines()?.forEach {
                        Log.i("Enderecos", it.toString())
                    }

                }
            }
            clientHttp.newCall(request).enqueue(response)
        }
    }
}