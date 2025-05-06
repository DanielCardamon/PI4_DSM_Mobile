package com.example.tetofinancas.recycler

import android.view.View
import android.widget.Button

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R

class AparelhosEletricosViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val nomeAparelhoE: TextView
    val valorAparelhoE: TextView
    val potenciaE: TextView
    val tempoUsoE: TextView
    val btnSalvarE: Button
    val btnexcluirE: Button
    init{
        nomeAparelhoE = view.findViewById(R.id.txt_nomeAparelhoE_row)
        valorAparelhoE = view.findViewById(R.id.txt_valorAparelhoE_row)
        potenciaE = view.findViewById(R.id.txt_potencia_row)
        tempoUsoE = view.findViewById(R.id.txt_tempoUsoAparelhoE_row)
        btnexcluirE = view.findViewById(R.id.btn_excluir_row)
        btnSalvarE = view.findViewById(R.id.btn_salvar_row)
    }
}