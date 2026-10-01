package com.example.restaurantes

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LotacaoTest {

    @Test
    fun comecaVazio() {
        val lotacao = Lotacao(capacidade = 3)
        assertEquals(0, lotacao.pessoas)
        assertTrue(lotacao.vazio)
        assertFalse(lotacao.lotado)
    }

    @Test
    fun naoPassaDaCapacidade() {
        val lotacao = Lotacao(capacidade = 2)
        assertTrue(lotacao.entrar())
        assertTrue(lotacao.entrar())
        assertTrue(lotacao.lotado)
        assertFalse(lotacao.entrar())
        assertEquals(2, lotacao.pessoas)
    }

    @Test
    fun naoFicaNegativo() {
        val lotacao = Lotacao(capacidade = 2)
        assertFalse(lotacao.sair())
        assertEquals(0, lotacao.pessoas)
    }

    @Test
    fun sairLiberaLugar() {
        val lotacao = Lotacao(capacidade = 2, pessoasIniciais = 2)
        assertTrue(lotacao.sair())
        assertEquals(1, lotacao.lugaresLivres)
        assertFalse(lotacao.lotado)
    }

    @Test
    fun valorInicialForaDoLimiteEhAjustado() {
        assertEquals(5, Lotacao(capacidade = 5, pessoasIniciais = 99).pessoas)
        assertEquals(0, Lotacao(capacidade = 5, pessoasIniciais = -3).pessoas)
    }
}
