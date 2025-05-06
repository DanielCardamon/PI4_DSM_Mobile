package com.example.tetofinancas.modelo

data class Outros (
    var codOutros: Long? = 0,
    var nomeServico: String = "",
    var valorServico: Double = 0.0,
    var complemento: String = ""
)