package android.ahmed.khaled.entities.responses

import android.ahmed.khaled.entities.remote.RemoteMovies
import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Khaled on 12/08/2021.
 */

data class MoviesResponse(

    @SerializedName("page")
    var page: Int = 0,

    @SerializedName("results")
    var results: List<RemoteMovies>? = null,

    @SerializedName("status_code")
    var statusCode: Int = 0,

    @SerializedName("success")
    var success: Boolean = true,
)