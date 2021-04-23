package com.cursoandroid.lifecycle_android

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataTimerViewModel(
    private val ONE_SECOND: Int = 1000,
    private val mElapsedTimer: MutableLiveData<Long> = MutableLiveData<Long>(),
    var InitialTime: Long? = SystemClock.elapsedRealtime(),
    private val timer: Timer? = Timer(),
) : ViewModel() {

    init{
        timer.scheduleAtFixedRate(() -> {
            val newValue: Long = (SystemClock.elapsedRealtime() -  mElapsedTimer) /1000
        }, ONE_SECOND,ONE_SECOND)
    }
}

