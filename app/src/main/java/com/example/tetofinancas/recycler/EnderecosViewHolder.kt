package com.example.tetofinancas.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R

class EnderecosViewHolder  (view: View): RecyclerView.ViewHolder(view) {
    var txtCep: TextView
    var txtRua: TextView
    var txtNumero: TextView
    var btnExcluir: TextView
    init {
        txtCep = view.findViewById(R.id.txt_cep_row)
        txtRua = view.findViewById(R.id.txt_rua_row)
        txtNumero = view.findViewById(R.id.txt_numero_row)
        btnExcluir = view.findViewById(R.id.btn_excluir_endereco_row)

    }
}