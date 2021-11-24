package com.ragnlabs.movies.repository

import com.ragnlabs.movies.api.ApiService
import com.ragnlabs.movies.models.MovieItem
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPopularMovies(page: Int): List<MovieItem> {
        return apiService.getPopularMovies(page = page).body()?.movieItems ?: emptyList()
    }
}
