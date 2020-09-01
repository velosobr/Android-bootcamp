package com.cursoandroid.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var dicesRolled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textRoll: TextView = findViewById(R.id.roll_text)
        var buttonRoll: Button = findViewById(R.id.roll_button)

        buttonRoll.setOnClickListener { rollDice(textRoll) }
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