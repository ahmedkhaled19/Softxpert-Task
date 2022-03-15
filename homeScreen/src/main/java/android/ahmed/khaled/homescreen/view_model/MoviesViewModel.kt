package android.ahmed.khaled.homescreen.view_model

import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.common.SingleLiveEvent
import android.ahmed.khaled.entities.local.Movie
import android.ahmed.khaled.entities.remote.Genre
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

class MoviesViewModel : BaseViewModel() {

    val genreListLiveData: SingleLiveEvent<Collection<Genre>> by lazy {
        SingleLiveEvent()
    }

    val moviesListLiveData: SingleLiveEvent<Collection<Movie>> by lazy {
        SingleLiveEvent()
    }

    var page: Int = 0

    var isLoading: Boolean = false
    var isLastPage: Boolean = false

    fun loadData() {
        if (page == 0) loadGenreData()

        TODO("Not yet implemented")
    }

    private fun loadGenreData() {

    }

    fun handleSelectedGenre(selectedPosition: Int) {
        TODO("Not yet implemented")
    }
}
