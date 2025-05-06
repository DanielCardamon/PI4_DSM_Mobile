package com.example.tetofinancas.recycler

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R

class ComprasViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val nomeItemC: TextView
    val valorItemC: TextView
    val quantidadeC: TextView
    val btnSalvarC: Button
    val btnexcluirC: Button
    init{
        nomeItemC = view.findViewById(R.id.txt_nomeItemCompra_row)
        valorItemC = view.findViewById(R.id.txt_valorCompra_row)
        quantidadeC = view.findViewById(R.id.txt_quantidade_row)
        btnexcluirC = view.findViewById(R.id.btn_excluir_row)
        btnSalvarC = view.findViewById(R.id.btn_editar_row)
    }
}