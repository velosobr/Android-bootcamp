package com.cursoandroid.rockpapersizer

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    var jogadasPossiveis = arrayOf("pedra", "papel", "tesoura")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun selecionadoPedra(view: View?) {
        selecionaJogada("pedra")
    }

    fun selecionadoPapel(view: View?) {
        selecionaJogada("papel")
    }



    fun selecionadoTesoura(view: View?) {
        selecionaJogada("tesoura")
    }

    fun selecionaJogada(jogadaUsuario: String) {
        val image = findViewById<ImageView>(R.id.jogadadopc)
        val text = findViewById<TextView>(R.id.textJogada)
        val jogadaPC = jogadaPC()

        when (jogadaPC) {
            "pedra" -> image.setImageResource(R.drawable.pedra)
            "papel" -> image.setImageResource(R.drawable.papel)
            "tesoura" -> image.setImageResource(R.drawable.tesoura)
        }

        if (jogadaPC == "tesoura" && jogadaUsuario == "papel" ||
                jogadaPC == "papel" && jogadaUsuario == "pedra" ||
                jogadaPC == "pedra" && jogadaUsuario == "tesoura") { //app ganhador
            text.text = "App ganhou"

        } else if ((jogadaUsuario == "tesoura" && jogadaPC == "papel") ||
                (jogadaUsuario == "papel" && jogadaPC == "pedra") ||
                (jogadaUsuario == "pedra" && jogadaPC == "tesoura")) { //usuario ganhador
            text.text = "Você ganhou"

        } else { //Empate
            text.text = "EMPATOU!!!"
        }
    }

    private fun jogadaPC(): String {
        val posicao = Random().nextInt(3)
        println(jogadasPossiveis[posicao])
        return jogadasPossiveis[posicao]
    }
}