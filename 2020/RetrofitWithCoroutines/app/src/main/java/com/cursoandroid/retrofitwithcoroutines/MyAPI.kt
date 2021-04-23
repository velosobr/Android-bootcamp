package com.cursoandroid.retrofitwithcoroutines

import retrofit2.Response
import retrofit2.http.GET

interface MyAPI {

    @GET("/comments")
    suspend fun getComments(): Response<List<Comment>>

}
