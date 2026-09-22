package com.example.movieapp.network

import com.example.movieapp.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

// Defines TMDB API endpoints

interface MovieApiService {
    // Retrieves popular movies with pagination support

    //todo review : move to constants no hardcoded

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MoviesResponse
    // Searches movies by title
    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String
    ): MoviesResponse
}




