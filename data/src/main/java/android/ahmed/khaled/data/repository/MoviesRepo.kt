package android.ahmed.khaled.data.repository

import android.ahmed.khaled.data.restful.MoviesEndPoints
import android.ahmed.khaled.entities.responses.GenresResponse
import android.ahmed.khaled.entities.responses.MoviesResponse
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

class MoviesRepo @Inject constructor(
    private val moviesEndPoints: MoviesEndPoints
) {

    suspend fun getGenresMovies(): GenresResponse {
        return moviesEndPoints.getGenresMovie()
    }

    suspend fun getTopRatedMovies(PageNumber : Int): MoviesResponse {
        return moviesEndPoints.getTopRatedMovies(PageNumber)
    }
}