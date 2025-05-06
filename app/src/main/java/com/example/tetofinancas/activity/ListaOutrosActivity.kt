package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.ListaOutrosBinding

import com.example.tetofinancas.modelo.Compras
import com.example.tetofinancas.modelo.Outros
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ListaOutrosActivity  : AppCompatActivity() {
    var ListaOutros = ArrayList<Outros>()
    val gson = Gson()
    private var clientHttp = OkHttpClient()
    private lateinit var binding: ListaOutrosBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListaOutrosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdicionar.setOnClickListener {
            var carregar = Intent(this, FormOutrosActivity::class.java)
            startActivity(carregar)
        }
        binding.btnVoltar.setOnClickListener {
            var carregar = Intent(this, HomeActivity::class.java)
            startActivity(carregar)
        }
    }
    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Outros.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Outros", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("Outros", "convertendo Outros")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Outros?>?>() {}.type
                val myMap: HashMap<String, Outros> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Outros>()
                myMap.keys.forEach {
                    var Itemoutros = myMap[it]
                    if (Itemoutros != null) {
                        Itemoutros.codOutros = it.toLong()
                        Log.i("Outros", "Itemoutros: $Itemoutros")
                        listaTemp.add(Itemoutros)
                    }
                }
                this@ListaOutrosActivity.runOnUiThread {
                    ListaOutros.clear()
                    ListaOutros.addAll(listaTemp)
                    binding.rcvListaOutros.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }


}