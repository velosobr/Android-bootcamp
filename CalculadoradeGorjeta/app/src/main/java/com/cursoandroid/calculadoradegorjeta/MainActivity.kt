@file:Suppress("UNUSED_VARIABLE")

package com.cursoandroid.calculadoradegorjeta

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var editValor: TextInputEditText
    private lateinit var textPorcentagem: TextView
    private lateinit var textGorjeta: TextView
    private lateinit var textTotal: TextView
    private lateinit var seekBarGorjeta: SeekBar

    private var porcentagem: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.editValor = findViewById(R.id.idEditValor)
        textPorcentagem = findViewById(R.id.idTextPorcentagem)
        textTotal = findViewById(R.id.idTextTotal)
        textGorjeta = findViewById<TextView>(R.id.idGorjeta)
        seekBarGorjeta = findViewById<SeekBar>(R.id.idSeekBar)

        seekBarGorjeta.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                porcentagem = progress.toDouble()
                textPorcentagem.text = "${porcentagem.roundToInt()}%"
                calcular(porcentagem)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }


        }

        )

    }

    @SuppressLint("SetTextI18n")
    fun calcular(porcentagem: Double) {
        val valorRecuperado = editValor.text.toString()

        if (valorRecuperado.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "Digite um valor Primeiro",
                Toast.LENGTH_LONG
            ).show()
        } else {
            //converter String para double
            val valorDigitado: Double = valorRecuperado.toDouble()

            //calcula a gorjeta total

            val gorjeta = (valorDigitado * (porcentagem / 100)).roundToInt()

            textGorjeta.text = "R$ $gorjeta"

            val total = (gorjeta + valorDigitado).roundToInt()
            textTotal.text = "R$ $total"


        }


    }
}
