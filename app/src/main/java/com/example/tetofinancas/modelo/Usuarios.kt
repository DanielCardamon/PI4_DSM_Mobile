package com.example.tetofinancas.modelo

import java.util.Date

data class Usuarios(
    var cpf: Number = 0,
    var nome: String = "",
    var email: String = "",
    var dataNasc: Date ,
    var senha: String ="",
    var msmSenha:String =""

)
