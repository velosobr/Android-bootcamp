package com.cursoandroid.retrofitwithcoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class TodoRepository {
    var client: Webservice = RetrofitClient.webservice

    fun getTodo(id: Int): LiveData<Todo> {
        val liveData = MutableLiveData<Todo>()

        client.getTodo(id).enqueue(object : Callback<Todo>, retrofit2.Callback<Todo> {
            override fun onResponse(call: Call<Todo>, response: Response<Todo>) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Todo>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return liveData
    }
}