package com.example.movieapp.model
// Represents a single movie object returned by TMDB API
data class Movie(

    val id: Int,

    val title: String,

    val overview: String,
    //todo review : use camel case for variable names here instead of snake case
    val poster_path: String,

    val vote_average: Double,

    val release_date: String

)