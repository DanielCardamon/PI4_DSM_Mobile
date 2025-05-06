package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.R
import com.example.tetofinancas.databinding.FormComprasBinding
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

class FormComprasActivity  : AppCompatActivity() {
    private lateinit var binding: FormComprasBinding
    private var clientHttp = OkHttpClient()
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCancelar.setOnClickListener {
            var carregar = Intent(this, ListaComprasActivity::class.java)
            startActivity(carregar)
        }
        binding.btnSalvar.setOnClickListener {
            val nomeItemCompra = findViewById<EditText>(R.id.edt_nomeItemCompra)
            val valorItemCompra = findViewById<EditText>(R.id.edt_valorItemCompra)
            val quantCompra = findViewById<EditText>(R.id.edt_quantidade)



            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                  {
 
  "valorItemCompra": ${valorItemCompra.text.toString().toDouble()},
  "quantidade": ${quantCompra.text},  
  "nomeItemCompra": ${nomeItemCompra.text}
}
                """.trimIndent()
            )

            val request = Request.Builder()
                .post(body)
                .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Compras")
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