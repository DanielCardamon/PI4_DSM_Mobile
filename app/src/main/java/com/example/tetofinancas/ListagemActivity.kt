package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.modelo.Contato
import com.example.myapplication.R
import android.util.Log
import com.example.myapplication.databinding.FormularioLayoutBinding
import com.example.myapplication.databinding.ListagemLayoutBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class ListagemActivity : AppCompatActivity() {
    val gson = Gson()
    val lista = ArrayList<Contato>()
    var clientHttp = OkHttpClient()
    lateinit var revContatos: RecyclerView
    lateinit var binding: ListagemLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListagemLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.listagem_layout)

        revContatos = findViewById<RecyclerView>(R.id.rcvContatos)
        val adapter = ContatoAdapter(this, lista)
        binding.apply {
            revContatos.adapter = adapter
            revContatos.layoutManager = LinearLayoutManager(this@ListagemActivity)

        }

        val btnFormulario = findViewById<Button>(R.id.btnFormulario)
        btnFormulario.setOnClickListener {
            val intent = Intent(this@ListagemActivity, FormularioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/agenda.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.i("AGENDA-CONTATO", "Dados recebidos convertendo")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Contato?>?>() {}.type
                val myMap: HashMap<String, Contato> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Contato>()
                myMap.keys.forEach {
                    val contato = myMap[it]
                    if (contato != null) {
                        contato.id = it
                        Log.i("AGENDA-CONTATO", "Contato: $it")
                        listaTemp.add(contato)
                    }

                }
                this@ListagemActivity.runOnUiThread(
                    java.lang.Runnable {
                        lista.clear()
                        lista.addAll(listaTemp)
                        revContatos.adapter?.notifyDataSetChanged()
                    }
                )
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }

}

