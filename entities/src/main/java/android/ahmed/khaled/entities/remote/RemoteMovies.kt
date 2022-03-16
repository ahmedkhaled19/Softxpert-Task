package android.ahmed.khaled.entities.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Khaled on 12/2/20.
 */

data class RemoteMovies(var id: Long) {

    @SerializedName("adult")
    var adult: Boolean? = false

    @SerializedName("backdrop_path")
    var backdrop_path: String? = ""

    @SerializedName("genre_ids")
    var genre_ids: List<Long>? = null

    @SerializedName("original_language")
    var original_language: String? = ""

    @SerializedName("original_title")
    var original_title: String? = ""

    @SerializedName("overview")
    var overview: String? = ""

    @SerializedName("popularity")
    var popularity: Double? = 0.0

    @SerializedName("poster_path")
    var poster_path: String? = ""

    @SerializedName("release_date")
    var release_date: String? = ""

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("video")
    var video: Boolean? = false

    @SerializedName("vote_average")
    var vote_average: Double? = 0.0

    @SerializedName("vote_count")
    var vote_count: Int? = 0

}