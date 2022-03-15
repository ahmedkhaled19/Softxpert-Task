package android.ahmed.khaled.core.bases

/**
 * Created by Ahmed Khaled on 12/2/20.
 */

interface BaseMapper<From, To> {
    fun convert(from: From?): To
}