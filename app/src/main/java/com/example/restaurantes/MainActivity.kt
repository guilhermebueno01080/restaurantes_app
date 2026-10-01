package com.example.restaurantes

import android.graphics.Color
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator

class MainActivity : AppCompatActivity() {

    private lateinit var lotacao: Lotacao

    private lateinit var status: TextView
    private lateinit var contador: TextView
    private lateinit var ocupacao: TextView
    private lateinit var progresso: LinearProgressIndicator
    private lateinit var entrar: MaterialButton
    private lateinit var sair: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.conteudo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        status = findViewById(R.id.pode_entrar_tx)
        contador = findViewById(R.id.contador_tx)
        ocupacao = findViewById(R.id.ocupacao_tx)
        progresso = findViewById(R.id.progresso)
        entrar = findViewById(R.id.entrar_bt)
        sair = findViewById(R.id.sair_bt)

        lotacao = Lotacao(CAPACIDADE_MAXIMA, savedInstanceState?.getInt(CHAVE_PESSOAS) ?: 0)
        progresso.max = lotacao.capacidade

        entrar.setOnClickListener { if (lotacao.entrar()) aoMudar(it) }
        sair.setOnClickListener { if (lotacao.sair()) aoMudar(it) }

        atualizarTela(animar = false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CHAVE_PESSOAS, lotacao.pessoas)
    }

    private fun aoMudar(botao: View) {
        botao.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        atualizarTela(animar = true)
    }

    private fun atualizarTela(animar: Boolean) {
        val corStatus = getColor(if (lotacao.lotado) R.color.status_lotado else R.color.status_livre)

        contador.text = lotacao.pessoas.toString()
        status.setText(if (lotacao.lotado) R.string.status_lotado else R.string.status_pode_entrar)
        status.setTextColor(corStatus)
        ocupacao.text = if (lotacao.lotado) {
            getString(R.string.ocupacao_lotado, lotacao.capacidade)
        } else {
            resources.getQuantityString(R.plurals.lugares_livres, lotacao.lugaresLivres, lotacao.lugaresLivres)
        }
        progresso.setIndicatorColor(corStatus)
        progresso.setProgressCompat(lotacao.pessoas, animar)

        entrar.isEnabled = !lotacao.lotado
        sair.isEnabled = !lotacao.vazio

        if (animar) pulsar(contador)
    }

    private fun pulsar(view: View) {
        view.animate().cancel()
        view.scaleX = 1.15f
        view.scaleY = 1.15f
        view.animate().scaleX(1f).scaleY(1f).setDuration(180).start()
    }

    companion object {
        private const val CAPACIDADE_MAXIMA = 20
        private const val CHAVE_PESSOAS = "pessoas"
    }
}
