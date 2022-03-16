package android.ahmed.khaled.entities.local

import android.os.Parcel
import android.os.Parcelable


data class Movie(
    var externalId: Long = 0,
    var movieTitle: String = "",
    var posterPath: String = "",
    var overview: String = "",
    var voteAverage: Double = 0.0,
    var genresIDs: MutableList<Long> = ArrayList(),
    var releaseDate: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        ArrayList(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(externalId)
        parcel.writeString(movieTitle)
        parcel.writeString(posterPath)
        parcel.writeString(overview)
        parcel.writeDouble(voteAverage)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}