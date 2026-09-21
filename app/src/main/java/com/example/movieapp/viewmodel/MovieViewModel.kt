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

    /**
     * Searches movies from TMDB API using the provided query.
     * This function runs on Dispatchers.IO to perform the network request
     * in the background, then updates the movies list with the search results.
     * @param query Search text entered by the user.
     */
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