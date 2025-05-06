package com.example.tetofinancas.modelo

data class Compras(
    var codCompras: Long? = 0,
    var nomeProduto: String = "",
    var valorProduto: Double = 0.0,
    var complemento: String = ""
)
