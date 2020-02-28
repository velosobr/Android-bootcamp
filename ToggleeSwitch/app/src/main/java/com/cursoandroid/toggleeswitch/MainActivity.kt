package com.cursoandroid.toggleeswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val toggleSenha by lazy {
        findViewById<ToggleButton>(R.id.idToggleButton)
    }
    lateinit var switchSenha: Switch
    lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchSenha = findViewById(R.id.idSwitch)
        textResultado = findViewById(R.id.idTextResult)
    }

    fun enviar(view: View?) {

        var resultado = textResultado.text
        if (switchSenha.isChecked) {
            resultado = "SWITCH ATIVADO"
        } else {
            resultado = "switch desativado"
        }
        if (toggleSenha.isChecked) {
            resultado += "\n Toggle ATIVADO"
        } else {
            resultado += "\n Toggle desativado"

        }

        textResultado.text = resultado

    }


}
