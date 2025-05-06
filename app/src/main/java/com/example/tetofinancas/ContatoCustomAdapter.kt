package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.modelo.Contato

class CustomAdapter(context: Context, list: ArrayList<Contato>,) : RecyclerView.Adapter<ContatoViewHolder>(){
    var lista : ArrayList<Contato>
    var inflater : LayoutInflater

    init {
        lista = list
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatoViewHolder {
        val view  = inflater.inflate(R.layout.contato_layout, null)
        return ContatoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ContatoViewHolder, position: Int) {
        val contato = lista[position]
        holder.txtNome.text = contato.nome
        holder.txtTell.text = contato.telefone
        holder.txtEmail.text = contato.email
    }

}