package com.cursoandroid.alcoolougasolina

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var campoPrecoAlcool: TextInputEditText
    lateinit var campoPrecoGasolina: TextInputEditText
    lateinit var campoResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoPrecoAlcool = findViewById<TextInputEditText?>(R.id.idAlcool)!!
        campoPrecoGasolina = findViewById<TextInputEditText?>(R.id.idGasolina)!!
        campoResultado = findViewById(R.id.idResultado)
    }


    @SuppressLint("SetTextI18n")
    fun calcularPreco(view: View) {

        //Recuperar os dados
        val precoGasolina = campoPrecoGasolina.text.toString();
        val precoAlcool = campoPrecoAlcool.text.toString();

        //validar os campos digitados
        if (this.validarCampos(precoAlcool,precoGasolina)) {

            var valorAlcool = precoAlcool.toDouble();
            var valorGasolina = precoGasolina.toDouble();

            /*
            Fazer cálculop de menor preço
            Se (ValorAlcool / valorGasolina) >= 0.7 é melhor utilizar gasolina
             senão é melhor utilizar alcool
             */

            if(valorAlcool/valorGasolina >=0.7)
                campoResultado.text = "É MELHOR UTILIZAR GASOLINA"
            else
                campoResultado.text = "É MELHOR UTILIZAR ALCOOL"



        }else{
            campoResultado.text = "Preencha os campos primeiro"
        }


    }

    private fun validarCampos(precoAlcool: String, precoGasolina: String): Boolean {

        return (precoAlcool.isNotEmpty() && precoGasolina.isNotEmpty())

    }

}
