package com.ragnlabs.movies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ragnlabs.movies.models.MovieItem
import com.ragnlabs.movies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val popularMoviesLiveData = MutableLiveData<List<MovieItem>>()

    fun getPopularMovies(page: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                movieRepository.getPopularMovies(page)
            }
            popularMoviesLiveData.value = movies
        }
    }
}
