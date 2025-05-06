package com.example.tetofinancas.repositorio

import android.util.Log
import com.example.tetofinancas.modelo.Compras
import com.example.tetofinancas.modelo.Outros
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class OutrosRepositorio {
    private var clientHttp = OkHttpClient()

    fun criarItemOutros(itemOutros: Outros) {

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorItemCompra": ${itemOutros.valorServico},
  "complementoItemOutros": ${itemOutros.complemento},
  "nomeItemCompra": ${itemOutros.nomeServico}
}
                """.trimIndent()
        )


        val request = Request.Builder()
            .post(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Outros.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Outros", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("Outros", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)

    }

    fun ApagarItemOutros(itemOutros: Outros) {
        val request = Request.Builder()
            .delete()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Outros/${itemOutros.codOutros}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Outros", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("Outros", "Item apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)


    }

    fun alterarItemOutros(itemOutros: Outros) {

        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorItemCompra": ${itemOutros.valorServico},
  "complementoItemOutros": ${itemOutros.complemento},
  "nomeItemCompra": ${itemOutros.nomeServico}
}
                """.trimIndent()
        )


        val request = Request.Builder()
            .post(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/Outros/${itemOutros.codOutros}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("Outros", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("Outros", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)
    }

}