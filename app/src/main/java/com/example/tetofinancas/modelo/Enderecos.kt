package com.example.tetofinancas.modelo

data class Enderecos(
    var codigoEnderco: Long? = 0,
    var cep: Int = 0,
    var cidade: String = "",
    var bairro: String = "",
    var rua: String = "",
    var numero: Int = 0,
    var complemento: String = ""
)
