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

class ContatoRepository {
    private var clientHttp = OkHttpClient()
    fun apagarContato(contato: Contato){
        val body = RequestBody.create(
            MediaType.parse("aplication/json"),
            """
                {
                
                }
            """.trimIndent()
        )

        val request = Request.Builder()
            .delete(body)
            .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/agenda/${contato.id}.json")
            .build()
        val response = object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e("AGENDA-CONTATO", e?.message.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()
                body?.charStream()?.buffered()?.lines()?.forEach {
                    Log.i("AGENDA-CONTATO", it.toString())
                }

            }
        }
        clientHttp.newCall(request).enqueue(response)
    }
}