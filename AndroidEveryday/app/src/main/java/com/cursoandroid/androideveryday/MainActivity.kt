package com.cursoandroid.androideveryday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var fullName: String = "";

        viewModel.firstName.observe(this, Observer {
            welcomeMessage.text = it
        })

        submitButton.setOnClickListener{
            fullName = firstNameEditText.text.toString() + lastNameEditText.text.toString()
            viewModel.firstName.value = fullName+", "+ ageEditText.text + " anos"
        }
    }
}