package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.databinding.ListaAparelhosEletricosBinding
import com.example.tetofinancas.modelo.aparelhos.AparelhosEletricos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ListaAparelhosEletricosActivity:AppCompatActivity() {
    var ListaAparelhosE = ArrayList<AparelhosEletricos>()
    val gson = Gson()
    private var clientHttp = OkHttpClient()
    private lateinit var binding: ListaAparelhosEletricosBinding
    override fun onCreate(bundle: Bundle?){

        super.onCreate(bundle)
        binding = ListaAparelhosEletricosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdicionar.setOnClickListener{
            var carregar = Intent(this, FormAparelhoEActivity::class.java)
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
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/aparelhos/eletricos.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("APARELHOS ELETRICOS", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("APARELHOS ELETRICOS", "convertendo aparelhos eletricos")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, AparelhosEletricos?>?>() {}.type
                val myMap: HashMap<String, AparelhosEletricos> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<AparelhosEletricos>()
                myMap.keys.forEach {
                    val aparelhoE = myMap[it]
                    if (aparelhoE != null) {
                        aparelhoE.codAparelhoEletrico = it.toLong()
                        Log.i("APARELHOS ELETRICOS", "Aparelho: $aparelhoE")
                        listaTemp.add(aparelhoE)
                    }
                }
                this@ListaAparelhosEletricosActivity.runOnUiThread {
                    ListaAparelhosE.clear()
                    ListaAparelhosE.addAll(listaTemp)
                    binding.rcvListaAparelhosEletricos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}