package com.example.movieapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.movieapp.model.Movie
import com.example.movieapp.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    var movies by mutableStateOf<List<Movie>>(emptyList())

    var currentPage = 1

    var isLoading = false

    init {
        loadMovies()
    }

    /**
     * Fetches popular movies from TMDB and updates the movie list.
     */
    // Load movies from TMDB API

    //todo review : add error handling and retry mechanism for network failures
    //todo review : add caching mechanism to store previously loaded movies to reduce network calls
    //todo review : add loading state for first load, pagination load, pull to refresh, and error handling
    //todo review : add empty state view when no results found

    fun loadMovies() {

        if (isLoading) return

        isLoading = true

        CoroutineScope(Dispatchers.IO).launch {

            try {

                val response =
                    RetrofitInstance.api.getPopularMovies(currentPage)

                movies = movies + response.results

                currentPage++

            } catch (_: Exception) {

            }

            isLoading = false
        }
    }


    // todo review : use flow to handle pagination and error handling
    /**
     * Searches movies from TMDB API using the provided query.
     * This function runs on Dispatchers.IO to perform the network request
     * in the background, then updates the movies list with the search results.
     * @param query Search text entered by the user.
     */
    // Search movies using TMDB API

    //todo review : add use-cases classes to handle these functionality and every use case should have one responsibility
    fun searchMovies(
        query: String
    ) {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                // Send search request to TMDB API
                val response =
                    RetrofitInstance.api.searchMovies(query)

                // Update movie list with search results
                movies = response.results

            } catch (_: Exception) {

                // Ignore errors to prevent app crash
            }
        }
    }}