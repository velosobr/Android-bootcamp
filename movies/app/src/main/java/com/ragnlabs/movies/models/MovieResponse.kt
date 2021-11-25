package com.ragnlabs.movies.models

data class MovieResponse(
    val page: Int,
    val movieItems: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
)
