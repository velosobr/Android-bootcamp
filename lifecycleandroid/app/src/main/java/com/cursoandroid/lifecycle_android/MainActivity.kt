package com.cursoandroid.lifecycle_android

import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometerViewModel = ViewModelProvider(this).get(ChronometerViewModel::class.java)

        var chronometer = findViewById<Chronometer>(R.id.chronometer)

            if (chronometerViewModel.mStartTime == null) {
                var startTime: Long = SystemClock.elapsedRealtime()
                chronometerViewModel.mStartTime = startTime
                chronometer.base = startTime
            } else {
                chronometer.base = chronometerViewModel.mStartTime
            }
            chronometer.start()
        })


    }
}