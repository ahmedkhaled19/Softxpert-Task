package android.ahmed.khaled.entities.responses

import android.ahmed.khaled.entities.remote.RemoteGenre
import com.google.gson.annotations.SerializedName


data class GenresResponse(
    @SerializedName("genres")
    var genresList: MutableList<RemoteGenre>? = null
)