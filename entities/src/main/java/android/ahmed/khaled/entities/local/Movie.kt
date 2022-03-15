package android.ahmed.khaled.entities.local


data class Movie(
    var externalId: Long = 0,
    var movieTitle: String = "",
    var posterPath: String = "",
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var genresIDs: MutableList<Int> = ArrayList(),
    var releaseDate: String = ""
)