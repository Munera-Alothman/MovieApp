package com.example.movieapp.model
// Main screen that displays movies and search functionality
data class MoviesResponse(

    val page: Int,

    val results: List<Movie>,
   //todo review : use camel case for variable names here instead of snake case
    val total_pages: Int,

    val total_results: Int

)