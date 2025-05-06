package com.example.tetofinancas.modelo.aparelhos

import java.time.LocalTime

data class AparelhosGas (
    val codAparelhoGas: Long? = 0,
    var valorAparelhoGas: Double = 0.0,
    var nomeAparelhoGas: String = "",
    var vazaoGas: Double = 0.0,
    var tempoUsoGas: LocalTime
    )