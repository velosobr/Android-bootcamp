package com.cursoandroid.coroutinebeginnerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val RESULT_1 = "RESULT #1"
    private val RESULT_2 = "RESULT #2"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                fakeApiRequest()
            }


        }


    }

    private fun setNewText(input: String){
        val newText = job_complete_text.text.toString() + "\n$input"
        job_complete_text.text = newText
    }

    private suspend fun setTextOnMainThread(input: String){
        withContext(Dispatchers.Main){
            setNewText(input)
        }
    }
    private suspend fun fakeApiRequest() {
        setTextOnMainThread(getResult1FromApi())
        setTextOnMainThread(getResult1FromApi2())

    }

    private suspend fun getResult1FromApi(): String {
        LogThread("getResult1FromApi")

        delay(1000)

        return RESULT_1
    }
    private suspend fun getResult1FromApi2(): String {
        LogThread("getResult1FromApi2")

        delay(5000)

        return RESULT_2
    }

    fun LogThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
    }
}
