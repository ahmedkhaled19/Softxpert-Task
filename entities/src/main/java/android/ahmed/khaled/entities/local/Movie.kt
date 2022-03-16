package android.ahmed.khaled.entities.local

import java.util.*


data class Movie(
    var externalId: Long = 0,
    var movieTitle: String = "",
    var posterPath: String = "",
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var genresIDs: MutableList<Long> = ArrayList(),
    var releaseDate: String = ""
)