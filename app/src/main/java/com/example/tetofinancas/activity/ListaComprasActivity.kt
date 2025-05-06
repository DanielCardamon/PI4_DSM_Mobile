package com.example.tetofinancas.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.databinding.ListaComprasBinding
import com.example.tetofinancas.modelo.Compras
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ListaComprasActivity : AppCompatActivity() {
    var ListaCompras = ArrayList<Compras>()
    val gson = Gson()
    private var clientHttp = OkHttpClient()
    private lateinit var binding: ListaComprasBinding
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = ListaComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdicionar.setOnClickListener {
            var carregar = Intent(this, FormComprasActivity::class.java)
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
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Compras.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Compras", e?.message.toString())
            }
            override fun onResponse(call: Call?, response: Response?) {
                Log.i("Compras", "convertendo Compras")
                val body = response?.body()
                val type = object : TypeToken<HashMap<String?, Compras?>?>() {}.type
                val myMap: HashMap<String, Compras> = gson.fromJson(body?.string(), type)
                val listaTemp = ArrayList<Compras>()
                myMap.keys.forEach {
                    var Itemcompra = myMap[it]
                    if (Itemcompra != null) {
                        Itemcompra.codCompras = it.toLong()
                        Log.i("Compras", "Itemcompra: $Itemcompra")
                        listaTemp.add(Itemcompra)
                    }
                }
                this@ListaComprasActivity.runOnUiThread {
                    ListaCompras.clear()
                    ListaCompras.addAll(listaTemp)
                    binding.rcvListaCompras.adapter?.notifyDataSetChanged()
                }
            }
        }
        clientHttp.newCall(request).enqueue(response)
    }

}