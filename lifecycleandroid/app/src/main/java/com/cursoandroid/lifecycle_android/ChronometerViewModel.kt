package com.cursoandroid.lifecycle_android

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChronometerViewModel: ViewModel() {

    var mStartTime = MutableLiveData<Long>()

    fun getStartTime(): LiveData<Long>{
        return mStartTime
    }
}