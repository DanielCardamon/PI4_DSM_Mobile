package com.example.myapplication

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContatoViewHolder(view : View): RecyclerView.ViewHolder(view) {
    lateinit var txtNome : TextView
    lateinit var txtTell : TextView
    lateinit var txtEmail : TextView
    lateinit var btnapagar : Button

    init{
        txtNome = view.findViewById<TextView>(R.id.txt_nome)
        txtTell = view.findViewById<TextView>(R.id.txt_tell)
        txtEmail = view.findViewById<TextView>(R.id.txt_email)
        btnapagar =view.findViewById<Button>(R.id.btn_apagar)
    }




}