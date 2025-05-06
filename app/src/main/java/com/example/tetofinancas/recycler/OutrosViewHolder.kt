package com.example.tetofinancas.recycler

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R

class OutrosViewHolder  (view: View): RecyclerView.ViewHolder(view) {
    val nomeItemO: TextView
    val valorItemO: TextView
    val complementoO: TextView
    val btnSalvarO: Button
    val btnexcluirO: Button
    init{
        nomeItemO = view.findViewById(R.id.txt_nomeItemOutros_row)
        valorItemO = view.findViewById(R.id.txt_valorOutros_row)
        complementoO = view.findViewById(R.id.txt_complemento_row)
        btnexcluirO = view.findViewById(R.id.btn_excluir_row)
        btnSalvarO = view.findViewById(R.id.btn_editar_row)
    }
}