package com.example.tetofinancas.modelo.aparelhos

import java.time.LocalTime


data class AparelhosAgua(
    val codAparelhoAgua: Long? = 0,
    var valorAparelhoAgua: Double = 0.0,
    var nomeAparelhoAgua: String = "",
    var vazaoAgua: Double = 0.0,
    var tempoUsoAgua: LocalTime
)
