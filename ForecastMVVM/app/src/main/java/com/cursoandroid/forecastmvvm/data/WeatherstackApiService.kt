package com.cursoandroid.forecastmvvm.data

import com.cursoandroid.forecastmvvm.data.network.response.CurrentWeatherResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "15867759534bc022e40b2e8a7df515c5"

//http://api.weatherstack.com/current?access_key=15867759534bc022e40b2e8a7df515c5&query=biguacu

interface WeatherstackApiService {
    @GET("current")
    fun getCurrentWeatherAsync(
        @Query("query") location: String
    ): Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(): WeatherstackApiService{
            val requestInterceptor = Interceptor{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                println("Este é o valor do: chain ${chain.request()}")
                println("Este é o valor do: url $url")
                println("Este é o valor do: chain.request() ${chain.request()}")
                println("Este é o valor do: request $request")

                return@Interceptor chain.proceed(request)
            }
            println("Este é o valor do: requestInterceptor $requestInterceptor")
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            println("Este é o valor do: okHttpClient ${okHttpClient.authenticator()}")

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherstackApiService::class.java)
        }
    }
}