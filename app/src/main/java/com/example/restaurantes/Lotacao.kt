package com.example.restaurantes

/**
 * Controla quantas pessoas estão dentro do restaurante, sem passar da capacidade.
 */
class Lotacao(val capacidade: Int, pessoasIniciais: Int = 0) {

    init {
        require(capacidade > 0) { "A capacidade precisa ser maior que zero" }
    }

    var pessoas: Int = pessoasIniciais.coerceIn(0, capacidade)
        private set

    val lotado: Boolean get() = pessoas >= capacidade
    val vazio: Boolean get() = pessoas == 0
    val lugaresLivres: Int get() = capacidade - pessoas

    /** Registra a entrada de uma pessoa. Retorna false se o restaurante já está lotado. */
    fun entrar(): Boolean {
        if (lotado) return false
        pessoas++
        return true
    }

    /** Registra a saída de uma pessoa. Retorna false se não há ninguém dentro. */
    fun sair(): Boolean {
        if (vazio) return false
        pessoas--
        return true
    }
}
