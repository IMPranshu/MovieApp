package com.impranshu.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.impranshu.movieapp.model.Movie
import com.impranshu.movieapp.model.getMovies

@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> (Unit) = {}
){

    var expandedState by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 10.dp, top = 4.dp, bottom = 4.dp)
            .fillMaxWidth()
//            .height(130.dp)
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 4.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(4.dp))

                )
            }
            Column(modifier = Modifier.padding(4.dp) ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.bodySmall
                )
                AnimatedVisibility(visible = expandedState) {
                    Column {
                        Text(
                            buildAnnotatedString {
                               withStyle(
                                   style = SpanStyle(
                                       color = Color.DarkGray,
                                       fontSize = 13.sp,
                                       fontWeight = FontWeight.Bold
                               )
                               ){
                                   append("Plot: ")
                               }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                ){
                                    append(movie.plot)
                                }
                        }, modifier = Modifier.padding(4.dp))

                        Divider(modifier = Modifier.padding(bottom = 4.dp))
                        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                        Text(text = "Actors  : ${movie.actors}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)


                    }
                }
                Icon(
                    imageVector = if (!expandedState) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Down Arrow",
                    modifier = if (!expandedState) Modifier
                        .absoluteOffset(x = 50.dp, y = 20.dp)
                        .size(25.dp)
                        .clickable {
                            expandedState = !expandedState

                        } else
                        Modifier
                            .absoluteOffset(x = 50.dp)
                            .size(25.dp)
                            .clickable {
                                expandedState = !expandedState

                            } ,
                    tint = Color.DarkGray
                )
            }




        }



    }
}


@Composable
fun MovieImages(newMovieList:List<Movie>){
    LazyRow{
        items(newMovieList[0].images){image ->
            Card(modifier = Modifier
                .padding(12.dp)
                .size(240.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(RoundedCornerShape(4.dp))

                )

            }
        }
    }
}
