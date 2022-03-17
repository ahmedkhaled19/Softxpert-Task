package android.ahmed.khaled.domain.fixtures

import android.ahmed.khaled.entities.remote.RemoteMovies

object MoviesFixture {

    var remoteMovie = RemoteMovies(1).apply {
        genre_ids = listOf(11, 22)
        title = "The one"
        poster_path = "/the_one_poster_image"
        overview = "a person who lost in the dark world"
        vote_average = 9.8
        release_date = "2004-06-29"
    }

    var remoteMovieWithNullTitle = RemoteMovies(2).apply {
        genre_ids = listOf(11, 22)
        title = null
        poster_path = "/the_one_poster_image"
        overview = "a person who lost in the dark world"
        vote_average = 9.8
        release_date = "2004-06-29"
    }

    var remoteMovieWithNullDate = RemoteMovies(1).apply {
        genre_ids = listOf(11, 22)
        title = "The one"
        poster_path = "/the_one_poster_image"
        overview = "a person who lost in the dark world"
        vote_average = 9.8
        release_date = null
    }


}