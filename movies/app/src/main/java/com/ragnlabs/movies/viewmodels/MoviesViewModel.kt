package com.ragnlabs.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ragnlabs.movies.models.Movie
import com.ragnlabs.movies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _popularMoviesList = MutableLiveData<List<Movie>>()
    val popularMoviesList: LiveData<List<Movie>>
        get() = _popularMoviesList

    private val _searchMovies = MutableLiveData<List<Movie>>()
    init {
        getPopularMovies()
    }

    private fun getPopularMovies(page: Int = 1) = runBlocking {

        movieRepository.getPopularMovies(page).let { moviesResponse ->

            if (moviesResponse.isSuccessful) {
                _popularMoviesList.postValue(moviesResponse.body()?.results)
            } else {
                Log.d(
                    "tag",
                    "occurred error on getPopularMovies: ${moviesResponse.code()} "
                )
            }
        }
    }

//    fun getPopularMoviesOtherWay(page: Int = 1) {
//        CoroutineScope(Dispatchers.Main).launch {
//            val movies = withContext(Dispatchers.Default) {
//                movieRepository.getPopularMovies(page)
//            }
//            _popularMoviesLiveData.value = movies.body()?.results
//        }
//    }
}
