package android.ahmed.khaled.data.restful

import android.ahmed.khaled.entities.responses.GenresResponse
import android.ahmed.khaled.entities.responses.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesEndPoints {

    @GET("genre/movie/list")
    suspend fun getGenresMovie(): GenresResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesResponse

}