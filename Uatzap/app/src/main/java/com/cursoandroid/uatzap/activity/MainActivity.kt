package com.cursoandroid.uatzap.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.cursoandroid.uatzap.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbarPrincipal)


        toolbar.title = "Whatsapp"

        setSupportActionBar(toolbar)


       // onCreateOptionsMenu()


    }
}
