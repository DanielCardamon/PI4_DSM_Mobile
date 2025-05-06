package com.example.tetofinancas.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R
import com.example.tetofinancas.modelo.Outros
import com.example.tetofinancas.repositorio.OutrosRepositorio
import java.util.ArrayList

class OutrosAdapter(
    private val context: Context,
    private val listaOutros: ArrayList<Outros>
) :
    RecyclerView.Adapter<OutrosViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var repositorio = OutrosRepositorio()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OutrosViewHolder {
        val view = inflater.inflate(
            R.layout.compras_componente,
            parent, false
        )
        return OutrosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaOutros.size
    }

    override fun onBindViewHolder(holder: OutrosViewHolder, position: Int) {
        val itemoutros = listaOutros[position]
        holder.nomeItemO.text = itemoutros.nomeServico
        holder.valorItemO.text = itemoutros.valorServico.toString()
        holder.complementoO.text = itemoutros.complemento
        holder.btnSalvarO.setOnClickListener {
            repositorio.alterarItemOutros(itemoutros)
        }
        holder.btnexcluirO.setOnClickListener {
            Log.i("COMPRAS", "Item selecionado para apagar: $itemoutros ")
            repositorio.ApagarItemOutros(itemoutros)
        }


    }
}