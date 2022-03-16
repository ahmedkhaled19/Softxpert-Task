package android.ahmed.khaled.domain.mappers

import android.ahmed.khaled.core.bases.BaseMapper
import android.ahmed.khaled.entities.local.Genre
import android.ahmed.khaled.entities.remote.RemoteGenre
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 16/03/2022.
 */

class GenreMapper @Inject constructor() : BaseMapper<RemoteGenre, Genre> {

    override fun convert(from: RemoteGenre?): Genre {
        return from?.let {
            Genre(
                it.id,
                it.name,
                false
            )
        } ?: Genre()
    }
}