package com.example.tetofinancas.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tetofinancas.R
import com.example.tetofinancas.modelo.aparelhos.AparelhosEletricos
import com.example.tetofinancas.repositorio.AparelhosEletricosRepositorio
import java.util.ArrayList

class AparelhoEletricoAdapter(private val context: Context, private val listaAparelhosE: ArrayList<AparelhosEletricos>) :
        RecyclerView.Adapter<AparelhosEletricosViewHolder>(){
        private var inflater:LayoutInflater = LayoutInflater.from(context)
        private var repositorio = AparelhosEletricosRepositorio()
        override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):AparelhosEletricosViewHolder{
            val view = inflater.inflate(
                R.layout.aparelho_eletrico_row,
                parent, false)
            return AparelhosEletricosViewHolder(view)

        }

    override fun getItemCount(): Int {
        return listaAparelhosE.size
    }

    override fun onBindViewHolder(holder: AparelhosEletricosViewHolder, position: Int) {
        val aparelhoEletrico = listaAparelhosE[position]
        holder.nomeAparelhoE.text = aparelhoEletrico.nomeAparelhoEletrico
        holder.valorAparelhoE.text = aparelhoEletrico.valorAparelhoEletrico.toString()
        holder.tempoUsoE.text = aparelhoEletrico.tempoUsoEletrico
        holder.potenciaE.text = aparelhoEletrico.potencia.toString()
        holder.btnSalvarE.setOnClickListener{
            TODO( /*  Criar activity de alterar aparelhos usando a layout form_aparelhos_eletricos*/)
        }
        holder.btnexcluirE.setOnClickListener {
            Log.i("APARELHOS-ELETRICOS", "Aparelho selecionado para apagar: $aparelhoEletrico ")
            repositorio.ApagarAparelhoE(aparelhoEletrico)
        }


    }

}
