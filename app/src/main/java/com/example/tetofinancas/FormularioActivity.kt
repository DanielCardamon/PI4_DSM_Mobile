package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.FormularioLayoutBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException

class FormularioActivity : AppCompatActivity() {

    private var clientHttp = OkHttpClient()
    private lateinit var binding: FormularioLayoutBinding


    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        binding = FormularioLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.formulario_layout)

        //  val btnListagem = findViewById<Button>(R.id.btn_listagem)
        binding.btnListagem.setOnClickListener {
            val intent = Intent(this, ListagemActivity::class.java)
            startActivity(intent)
        }

        //val btnGravar = findViewById<Button>(R.id.btn_gravar)
        binding.btnGravar.setOnClickListener {
            val txtNome = findViewById<EditText>(R.id.edt_nome)
            val txtTelefone = findViewById<EditText>(R.id.edt_tell)
            val txtEmail = findViewById<EditText>(R.id.edt_email)

            val body = RequestBody.create(
                MediaType.parse("application/json"),
                """
                    {
                        "nome": "${txtNome.text}",
                        "telefone": "${txtTelefone.text}",
                        "email": "${txtEmail.text}"
                    }
                """.trimIndent()
            )
            val request = Request.Builder()
                .post(body)
                .url("https://test-apirest-762a1-default-rtdb.firebaseio.com/agenda.json")
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
}

