package com.example.tetofinancas.repositorio

import android.util.Log
import android.widget.Toast
import com.example.tetofinancas.modelo.aparelhos.AparelhosEletricos
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class AparelhosEletricosRepositorio {
    private var clientHttp = OkHttpClient()

    fun criarAparelhoE(aparelhoEletrico: AparelhosEletricos) {
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorAparelho": ${aparelhoEletrico.valorAparelhoEletrico},
  "potencia": ${aparelhoEletrico.potencia},
  "tempoUsoEletrico": ${aparelhoEletrico.tempoUsoEletrico},
  "nomeAparelho": ${aparelhoEletrico.nomeAparelhoEletrico}
}
                """.trimIndent()
        )

        val request = Request.Builder()
            .post(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/aparelhos/eletricos.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("APARELHOS_ELETRICOS", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("APARELHOS_ELETRICOS", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)

    }

    fun ApagarAparelhoE(aparelhoEletrico: AparelhosEletricos) {
        val request = Request.Builder()
            .delete()
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/aparelhos/eletricos/${aparelhoEletrico.codAparelhoEletrico}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("APARELHOS_ELETRICOS", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                Log.e("APARELHOS_ELETRICOS", "Aparelho apagado com sucesso")
            }
        }
        clientHttp.newCall(request).enqueue(response)


    }

    fun alterarAparelhoE(aparelhoEletrico: AparelhosEletricos) {
        val body = RequestBody.create(
            MediaType.parse("application/json"),
            """
                  {
 
  "valorAparelho": ${aparelhoEletrico.valorAparelhoEletrico},
  "potencia": ${aparelhoEletrico.potencia},
  "tempoUsoEletrico": ${aparelhoEletrico.tempoUsoEletrico},
  "nomeAparelho": ${aparelhoEletrico.nomeAparelhoEletrico}
}
                """.trimIndent()
        )

        val request = Request.Builder()
            .post(body)
            .url("http://localhost:8090/enderecos/aparelhos/eletricos/alterar/${aparelhoEletrico.codAparelhoEletrico}")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("APARELHOS_ELETRICOS", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val localBody = response?.body()
                localBody?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("APARELHOS_ELETRICOS", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)

    }


}