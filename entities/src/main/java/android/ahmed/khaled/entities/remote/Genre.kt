package android.ahmed.khaled.entities.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

data class Genre(
    var id: Long,
    @SerializedName("name")
    var name: String
)