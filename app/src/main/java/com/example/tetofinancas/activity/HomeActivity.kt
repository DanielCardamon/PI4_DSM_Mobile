package com.example.tetofinancas.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tetofinancas.databinding.LayoutHomeBinding
import com.example.tetofinancas.modelo.Enderecos
import com.example.tetofinancas.modelo.aparelhos.AparelhosEletricos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HomeActivity:AppCompatActivity() {
    private lateinit var binding: LayoutHomeBinding
    var ListaEnderecos = ArrayList<Enderecos>()
    val gson = Gson()
    private var clientHttp = OkHttpClient()
    override fun onCreate(bundle:Bundle?) {
        super.onCreate(bundle)
        binding=LayoutHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onStart() {
        super.onStart()
        val request = Request.Builder()
            .get()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/enderecos.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("ENDERECOS", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("ENDERECOS", "convertendo enderecos")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Enderecos?>?>() {}.type
                val myMap: HashMap<String, Enderecos> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Enderecos>()
                myMap.keys.forEach {
                    var endereco = myMap[it]
                    if (endereco != null) {
                        endereco.codigoEnderco = it.toLong()
                        Log.i("ENDERECO", "Endereco: $endereco")
                        listaTemp.add(endereco)
                    }
                }
                this@HomeActivity.runOnUiThread {
                    ListaEnderecos.clear()
                    ListaEnderecos.addAll(listaTemp)
                    binding.rcvListaEnderecos.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
    fun ApagarAparelhoE(endereco: Enderecos) {
        val request = Request.Builder()
            .delete()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/enderecos/${endereco.codigoEnderco}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("ENDERECO", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("ENDERECO", "ENDERECO apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)


    }
}