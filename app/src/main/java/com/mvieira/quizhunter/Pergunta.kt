package com.mvieira.quizhunter

data class Pergunta(
    var id: Int,
    var opcA: String,
    var opcB: String,
    var opcC: String,
    var opcD: String,
    var resposta: Int,
    var imagem: Int
)