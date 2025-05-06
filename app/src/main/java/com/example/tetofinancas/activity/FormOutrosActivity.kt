package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.R
import com.example.tetofinancas.databinding.FormOutrosBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class FormOutrosActivity   : AppCompatActivity() {
    private lateinit var binding: FormOutrosBinding
    private var clientHttp = OkHttpClient()
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormOutrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancelar.setOnClickListener {
            var carregar = Intent(this, ListaOutrosActivity::class.java)
            startActivity(carregar)
        }
        binding.btnSalvar.setOnClickListener {
            val nomeItemOutros = findViewById<EditText>(R.id.edt_nomeItemOutros)
            val valorItemOutros = findViewById<EditText>(R.id.edt_valorItemOutros)
            val complementoItemOutros = findViewById<EditText>(R.id.edt_Complemento)



            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                  {
 
  "nomeItemOutros": ${valorItemOutros.text.toString().toDouble()},
  "complementoItemOutros": ${complementoItemOutros.text},
  "nomeItemCompra": ${nomeItemOutros.text}
}
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Outros")
                .build()
            val response = object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {
                    Log.e("compras", e?.message.toString())
                }

                override fun onResponse(call: Call?, response: Response?) {
                    val localBody = response?.body()
                    localBody?.charStream()?.buffered()?.lines()?.forEach {
                        Log.i("compras", it.toString())
                    }

                }
            }
            clientHttp.newCall(request).enqueue(response)
        }
    }
}