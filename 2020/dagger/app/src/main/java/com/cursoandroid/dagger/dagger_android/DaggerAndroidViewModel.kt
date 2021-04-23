package com.cursoandroid.dagger.dagger_android

import com.cursoandroid.dagger.dagger_android.data.LoginRepository
import javax.inject.Inject

class DaggerAndroidViewModel @Inject constructor(private val repository: LoginRepository) {
}