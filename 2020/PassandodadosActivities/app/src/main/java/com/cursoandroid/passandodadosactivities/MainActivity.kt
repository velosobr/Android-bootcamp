package com.cursoandroid.passandodadosactivities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {

    lateinit var buttonEnviar: Button

    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonEnviar = findViewById(R.id.idButtonPassarDados)

        buttonEnviar.setOnClickListener {

            intent = Intent(this, SegundaActivity::class.java)

            intent.putExtra("nome","Lino")
            intent.putExtra("idade",26)

            startActivity(intent)


            //  Toast.makeText(this, "Just a test",Toast.LENGTH_SHORT).show()
        }
    }
}
