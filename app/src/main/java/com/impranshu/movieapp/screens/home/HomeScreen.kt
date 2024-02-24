package com.impranshu.movieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.impranshu.movieapp.model.Movie
import com.impranshu.movieapp.model.getMovies
import com.impranshu.movieapp.navigation.MovieScreens
import com.impranshu.movieapp.widgets.MovieRow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("MovieApp")
            }
        )
    }) {
        MainContent(navController = navController)

    }

}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
){

    Column(modifier = Modifier.padding(top = 70.dp, start = 10.dp)) {
        LazyColumn {
            items(items = movieList){
                MovieRow(movie = it){movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
//                    Log.d("Movie", "MainContent: $movie")

                }
            }
        }

    }
}