package com.ragnlabs.movies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ragnlabs.movies.models.MovieItem
import com.ragnlabs.movies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMoviesLiveData = MutableLiveData<List<MovieItem>>()
    val popularMoviesLiveData: LiveData<List<MovieItem>>
        get() = _popularMoviesLiveData

    init {
        getPopularMovies()
    }

    private fun getPopularMovies(page: Int = 1) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                movieRepository.getPopularMovies(page)?.let { moviesList ->
                    _popularMoviesLiveData.value = moviesList
                }
            }
        }
    }
}
