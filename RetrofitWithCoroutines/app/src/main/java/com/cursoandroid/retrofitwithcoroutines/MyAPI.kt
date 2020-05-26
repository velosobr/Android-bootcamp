package com.cursoandroid.retrofitwithcoroutines

import retrofit2.Call
import retrofit2.http.GET

interface MyAPI {

    @GET("/comments")
    fun getComments(): Call<List<Comment>>

}
