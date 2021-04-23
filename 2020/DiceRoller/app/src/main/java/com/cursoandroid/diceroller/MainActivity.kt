package com.cursoandroid.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var dicesRolled = false
    var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textRoll: TextView = findViewById(R.id.roll_text)
        var buttonRoll: Button = findViewById(R.id.roll_button)
        var buttonCount: Button = findViewById(R.id.countUp_button)

        buttonRoll.setOnClickListener { rollDice(textRoll) }

        buttonCount.setOnClickListener{countUp(textRoll)}
    }

    private fun countUp(text: TextView) {
        if(text.text.toString() == "Hello World!"){
            text.text = 0.toString()
            contador++
        }else{
            text.text = contador.toString()
            contador++
        }
    }

    private fun rollDice(text: TextView) {

        if(!dicesRolled){
            text.text = "The Dice has rolled"
            dicesRolled = true
        }else{
            text.text = "The Dice has already rolled once"

            Toast.makeText(this, "Botão clicado", Toast.LENGTH_SHORT).show()
        }



    }


}