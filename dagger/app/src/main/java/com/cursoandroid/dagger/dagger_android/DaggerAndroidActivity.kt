package com.cursoandroid.dagger.dagger_android

import android.os.Bundle
import android.os.PersistableBundle
import com.cursoandroid.dagger.databinding.ActivityDaggerAndroidBinding


import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

class DaggerAndroidActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaggerAndroidBinding

    @Inject
    lateinit var loginViewModel: DaggerAndroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.
    }
}