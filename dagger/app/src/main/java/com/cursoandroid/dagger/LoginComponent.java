package com.cursoandroid.dagger;

import dagger.Subcomponent;

@Subcomponent
interface LoginComponent {

    //Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: DaggerAndroidActivity)
}
