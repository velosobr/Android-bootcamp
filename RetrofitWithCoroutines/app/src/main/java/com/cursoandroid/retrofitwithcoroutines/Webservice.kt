package com.cursoandroid.retrofitwithcoroutines

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// GET https://jsonplaceholder.typicode.com/todos/1
interface Webservice {
    @GET("/todos/{id}")
    fun getTodo(@Path(value = "id") todoId: Int): Call<Todo>

}