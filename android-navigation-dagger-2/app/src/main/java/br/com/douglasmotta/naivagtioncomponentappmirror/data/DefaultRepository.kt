package br.com.douglasmotta.naivagtioncomponentappmirror.data

import android.util.Log

class DefaultRepository: Repository {
    override fun doLogin() {
        Log.d("DefaultRepository", "Login done")
    }
}