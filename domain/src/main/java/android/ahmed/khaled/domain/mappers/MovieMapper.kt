package android.ahmed.khaled.domain.mappers

import android.ahmed.khaled.core.bases.BaseMapper
import android.ahmed.khaled.core.utils.Constants
import android.ahmed.khaled.entities.local.Movie
import android.ahmed.khaled.entities.remote.RemoteMovies
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 16/03/2022.
 */

class MovieMapper @Inject constructor() : BaseMapper<RemoteMovies?, Movie> {

    override fun convert(from: RemoteMovies?): Movie {
        return from?.let {
            Movie(
                it.id,
                it.title ?: "",
                Constants.IMAGE_BASE_URL + it.poster_path,
                it.overview ?: "",
                it.vote_average ?: 0.0,
                it.genre_ids!!.toMutableList(),
                it.release_date ?: ""
            )
        } ?: Movie()
    }

}