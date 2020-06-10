package com.cursoandroid.retrofitwithcoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)

        /**  GlobalScope.launch(Dispatchers.IO) {
        val response = api.getComments()
        for (comment in response.body()!!) {
        Log.d(TAG, comment.toString())
        }

        }
         */
        Log.d(TAG, "Started")
        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getComments()
            for (comment in response.body()!!) {
                Log.d(TAG, comment.toString())
            val time = measureTimeMillis {

                //the best practice
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }
                Log.d(TAG, "Answer1 is ${answer1.await()}")
                Log.d(TAG, "Answer2 is ${answer2.await()}")
                /**
                 * its not the best practice
                 * var answer1: String? = null
                var answer2: String? = null
                val job1 = launch { answer1 = networkCall1() }
                val job2 = launch { answer2 = networkCall2() }

                job1.join()
                job2.join()
                Log.d(TAG, "Answer1 is $answer1")
                Log.d(TAG, "Answer2 is $answer2")
                 */

            }
            Log.d(TAG, "Requests took $time ms")

        }
    }

    suspend fun networkCall1(): String {
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "Answer 2"
    }
}
