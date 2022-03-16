package android.ahmed.khaled.domain.usecases

import android.ahmed.khaled.core.bases.BaseNetworkError
import android.ahmed.khaled.data.repository.MoviesRepo
import android.ahmed.khaled.domain.bases.BaseRemoteUseCase
import android.ahmed.khaled.domain.mappers.MovieMapper
import android.ahmed.khaled.entities.local.Movie

import android.ahmed.khaled.entities.responses.MoviesResponse
import javax.inject.Inject

class MoviesRemoteUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val moviesMapper: MovieMapper,
    errorHandler: BaseNetworkError
) : BaseRemoteUseCase<Int, MoviesResponse, MutableList<Movie>>(errorHandler) {

    override suspend fun executeRequest(parameters: Int): MoviesResponse {
        return moviesRepo.getTopRatedMovies(parameters)
    }

    override suspend fun convert(remoteResponse: MoviesResponse): MutableList<Movie> {
        val localMoviesList = mutableListOf<Movie>()

        remoteResponse.results?.forEach {
            localMoviesList.add(moviesMapper.convert(it))
        }

        return localMoviesList
    }
}