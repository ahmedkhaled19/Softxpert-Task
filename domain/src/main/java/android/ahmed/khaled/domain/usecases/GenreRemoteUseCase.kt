package android.ahmed.khaled.domain.usecases

import android.ahmed.khaled.core.bases.BaseNetworkError
import android.ahmed.khaled.core.utils.Constants
import android.ahmed.khaled.data.repository.MoviesRepo
import android.ahmed.khaled.domain.bases.BaseRemoteUseCase
import android.ahmed.khaled.domain.mappers.GenreMapper
import android.ahmed.khaled.entities.local.Genre
import android.ahmed.khaled.entities.responses.GenresResponse
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 16/03/2022.
 */

class GenreRemoteUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val genreMapper: GenreMapper,
    errorHandler: BaseNetworkError
) : BaseRemoteUseCase<Unit, GenresResponse, MutableList<Genre>>(errorHandler) {

    override suspend fun executeRequest(parameters: Unit): GenresResponse {
        return moviesRepo.getGenresMovies()
    }

    override suspend fun convert(remoteResponse: GenresResponse): MutableList<Genre> {
        val localMoviesList = mutableListOf<Genre>()

        localMoviesList.add(Genre(Constants.GENRE_BASE_ID.toLong(), "All", true))

        remoteResponse.genresList?.forEach {
            localMoviesList.add(genreMapper.convert(it))
        }

        return localMoviesList
    }
}