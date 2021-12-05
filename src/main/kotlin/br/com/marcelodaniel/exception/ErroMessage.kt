package br.com.marcelodaniel.exception

import java.time.LocalDateTime

data class ErroMessage(
    var dataHora: String = LocalDateTime.now().toString(),
    var msg: String? = ""
)
