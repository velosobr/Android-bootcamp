package com.cursoandroid.dagger

import android.app.Application

class App : Application() {
    val appComponent: AppComponent by lazy {
        intializeComponent()
    }

    open fun intializeComponent(): AppComponent =
        DaggerAppComponent.factory().create(applicationContext)
}