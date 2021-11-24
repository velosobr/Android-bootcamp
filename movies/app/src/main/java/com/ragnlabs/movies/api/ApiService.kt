package com.ragnlabs.movies.api

import com.ragnlabs.movies.helper.LocalData
import com.ragnlabs.movies.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = LocalData.API_KEY,
        @Query("page") page: Int
    ): Response<MovieResponse>
}
