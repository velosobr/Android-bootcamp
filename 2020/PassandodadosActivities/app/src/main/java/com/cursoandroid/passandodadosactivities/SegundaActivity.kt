package com.cursoandroid.passandodadosactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class SegundaActivity : AppCompatActivity() {

    lateinit var campoNome: TextView
    lateinit var campoIdade: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        campoNome = findViewById(R.id.idTextNome)
        campoIdade = findViewById(R.id.idTextIdade)

        var bundle: Bundle? = intent.extras

        if (bundle != null) {
            campoNome.text = bundle.get("nome").toString()
            campoIdade.text = bundle.get("idade").toString()
        }

    }
}
