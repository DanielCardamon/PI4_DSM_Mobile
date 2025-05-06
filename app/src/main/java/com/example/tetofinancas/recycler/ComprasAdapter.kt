package com.example.tetofinancas.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R
import com.example.tetofinancas.modelo.Compras
import com.example.tetofinancas.repositorio.ComprasRepositorio
import java.util.ArrayList

class ComprasAdapter(
    private val context: Context,
    private val listaCompras: ArrayList<Compras>
) :
    RecyclerView.Adapter<ComprasViewHolder>() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var repositorio = ComprasRepositorio()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComprasViewHolder {
        val view = inflater.inflate(
            R.layout.compras_componente,
            parent, false
        )
        return ComprasViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaCompras.size
    }

    override fun onBindViewHolder(holder: ComprasViewHolder, position: Int) {
        val itemcompras = listaCompras[position]
        holder.nomeItemC.text = itemcompras.nomeProduto
        holder.valorItemC.text = itemcompras.valorProduto.toString()
        holder.quantidadeC.text = itemcompras.complemento.toString()
        holder.btnSalvarC.setOnClickListener {
            repositorio.alterarItemCompras(itemcompras)
        }
        holder.btnexcluirC.setOnClickListener {
            Log.i("Compras", "Item selecionado para apagar: $itemcompras ")
            repositorio.ApagarItemCompras(itemcompras)
        }


    }
}