package com.cursoandroid.uatzap.config

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object ConfiguracaoFirebase {

    private var database: DatabaseReference? = null
    private var auth: FirebaseAuth? = null


    //retorna a instância do FirebaseDatabase
    fun getFirebaseDatabase(): DatabaseReference {
        return database ?: FirebaseDatabase.getInstance().reference

    }
    //retorna a instancia do FirebaseAuth

    fun getFirebaseAutenticacao(): FirebaseAuth {
        return auth ?: FirebaseAuth.getInstance()
    }

}