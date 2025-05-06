package com.example.tetofinancas.modelo.aparelhos

import java.time.LocalTime


data class AparelhosEletricos (
    var codAparelhoEletrico: Long? = 0,
    var valorAparelhoEletrico: Double = 0.0,
    var nomeAparelhoEletrico: String = "",
    var potencia: Double = 0.0,
    var tempoUsoEletrico: String = ""
    )