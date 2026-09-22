package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.MovieViewModel
import androidx.compose.foundation.layout.fillMaxWidth
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val movieViewModel: MovieViewModel = viewModel()

            MovieAppTheme {
                MovieHomeScreen(movieViewModel)
            }
        }
    }
}


/*todo review move this to a separate file, keep composables in different files for better readability and maintainability.
 keep it as following
   ui/
  screens/
    home/
      HomeScreen.kt        <- MovieHomeScreen composable
  components/
    MovieItem.kt           <- the Card/Row movie item (reusable, e.g. in detail nav)
  theme/                   <- already exists
*
* */

//todo review : explore material design and see how search with app bar is handled and app bars are handled
@Composable
fun MovieHomeScreen(
    movieViewModel: MovieViewModel
) {

    // todo: review check if this will survive configuration changes
    var searchText by remember {
        mutableStateOf("")
    }

    // Debounce search requests to avoid API calls on every keystroke
    LaunchedEffect(searchText) {
// todo: review  use flow and debounce
// todo: review  if i typed 4 chars then removed them before loading fires, it still refresh the data, prevent this
        // Wait until the user stops typing
        delay(1500)

        // Search if the text is not empty
        if (searchText.isNotBlank()) {

            movieViewModel.searchMovies(searchText)

        } else {

            // Reset movie list and reload popular movies
            movieViewModel.movies = emptyList()
            movieViewModel.currentPage = 1
            movieViewModel.loadMovies()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
//todo: review move to strings and resources
        Text(
            text = "Movie App"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                //todo: review move to strings and resources
                Text("Search Movie")
            }
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(
                movieViewModel.movies
            ) { index, movie ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    )
                ) {

                    Row(
                        modifier = Modifier.padding(12.dp)
                    ) {
//todo: review move to build config as url
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                            contentDescription = movie.title,
                            modifier = Modifier
                                .height(150.dp)
                                .width(100.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        Column {

                            Text(
                                text = movie.title,
                                maxLines = 2
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "⭐ ${movie.vote_average}"
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = movie.release_date
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                }

                if (
                    searchText.isEmpty() &&
                    index == movieViewModel.movies.lastIndex
                ) {

                    movieViewModel.loadMovies()
                }
            }
        }
    }
}
//todo : INV 1- understand the architecure , coroutines , retrofit module ,
//todo : Fix : on search it should be handled as interval or dedub
// todo: Fix : we need to introduce the usecases
// todo : document the code


//test
//testt2
//test
