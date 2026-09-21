package com.example.movieapp.model
// Main screen that displays movies and search functionality
data class MoviesResponse(

    val page: Int,

    val results: List<Movie>,

    val total_pages: Int,

    val total_results: Int

)