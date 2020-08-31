package com.cursoandroid.lifecycle_android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class LiveDataTimerViewModel : ViewModel(){

    private val ONE_SECOND = 1000
    private val mElapsedTime = MutableLiveData<Long>()
    var InitialTime: Long? = null
    private val timer: Timer? = null
}
