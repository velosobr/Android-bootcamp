package com.cursoandroid.lifecycle_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chronometerViewModel: ChronometerViewModel by viewModels()

        var chronometer = findViewById<Chronometer>(R.id.chronometer)

        chronometerViewModel.mStartTime.observe(this, Observer<Long> { mStartTime ->
            if (mStartTime == null) {
                var startTime = (MutableLiveData<Long>()) SystemClock?.elapsedRealtime()
                chronometerViewModel.mStartTime = startTime
                chronometer.base = startTime
            } else {
                chronometer.base = chronometerViewModel.mStartTime!!
            }
            chronometer.start()
        })





    }
}