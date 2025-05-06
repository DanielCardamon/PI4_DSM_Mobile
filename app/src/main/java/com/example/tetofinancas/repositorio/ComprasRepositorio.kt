package com.example.tetofinancas.repositorio

import android.util.Log
import com.example.tetofinancas.modelo.Compras
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class ComprasRepositorio {
    private var clientHttp = OkHttpClient()

    fun criarItemC(itemCompras: Compras) {

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorItemCompra": ${itemCompras.valorProduto},
  "quantidade": ${itemCompras.complemento},  
  "nomeItemCompra": ${itemCompras.nomeProduto}
}
                """.trimIndent()
        )


        val request = Request.Builder()
            .post(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Compras.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Compras", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("Compras", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)

    }

    fun ApagarItemCompras(itemCompras: Compras) {
        val request = Request.Builder()
            .delete()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Compras/${itemCompras.codCompras}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Compras", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("Compras", "Item apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)


    }

    fun alterarItemCompras(itemCompras: Compras) {

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorItemCompra": ${itemCompras.valorProduto},
  "quantidade": ${itemCompras.complemento},  
  "nomeItemCompra": ${itemCompras.nomeProduto}
}
                """.trimIndent()
        )


        val request = Request.Builder()
            .post(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Compras/${itemCompras.codCompras}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Compras", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("Compras", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)

    }

}