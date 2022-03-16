package android.ahmed.khaled.homescreen.view_model

import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.common.SingleLiveEvent
import android.ahmed.khaled.core.utils.Constants.GENRE_BASE_ID
import android.ahmed.khaled.core.utils.NetworkingUtils
import android.ahmed.khaled.domain.usecases.GenreRemoteUseCase
import android.ahmed.khaled.domain.usecases.MoviesRemoteUseCase
import android.ahmed.khaled.entities.local.Genre
import android.ahmed.khaled.entities.local.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRemoteUseCase: MoviesRemoteUseCase,
    private val genreRemoteUseCase: GenreRemoteUseCase
) : BaseViewModel() {

    val genreListLiveData: SingleLiveEvent<MutableList<Genre>> by lazy {
        SingleLiveEvent()
    }

    val moviesListLiveData: SingleLiveEvent<MutableList<Movie>> by lazy {
        SingleLiveEvent()
    }

    private var localMoviesList: MutableList<Movie> = ArrayList()
    var page: Int = 0
    var selectedGenrePosition = 0
    var isLoading: Boolean = false
    var isLastPage: Boolean = false

    fun loadData() {
        if (!NetworkingUtils.isNetworkConnected) {
            showLoadingProgressBar.value = false
            showMessageByStringId(
                android.ahmed.khaled.core.R.string.check_connection,
                withAction = true
            )
            return
        }

        if (page == 0) loadGenreData()

        page++
        moviesRemoteUseCase.sendRequest(page) {
            onComplete { moviesList ->
                if (moviesList.isEmpty()) {
                    isLastPage = true
                    return@onComplete
                }

                localMoviesList.addAll(moviesList)
                handleShowData(moviesList)
            }

            onError { errorMessage ->
                showTheErrorMessage(errorMessage)
            }

            isLoading {
                isLoading = it

                if (page == 1) {
                    showLoadingProgressBar.value = it
                } else {
                    showLoadingMoreProgressBar.value = it
                }
            }
        }
    }

    private fun loadGenreData() {
        genreRemoteUseCase.sendRequest(Unit) {
            onComplete {
                genreListLiveData.value = it
                handleShowData(localMoviesList)
            }

            onError {
                showTheErrorMessage(it)
            }
        }
    }

    private fun handleShowData(remoteMoviesList: MutableList<Movie>) {
        if (genreListLiveData.value.isNullOrEmpty() || remoteMoviesList.isNullOrEmpty()) return

        if (selectedGenrePosition == GENRE_BASE_ID) {
            moviesListLiveData.value = remoteMoviesList
            return
        }

        val selectedGenreID = genreListLiveData.value!![selectedGenrePosition].id
        val filteredMoviesList: MutableList<Movie> =
            remoteMoviesList.map { it.copy() }.toMutableList()

        val resultList: MutableList<Movie> = filteredMoviesList.filter {
            selectedGenreID in it.genresIDs
        }.toMutableList()

        moviesListLiveData.value = resultList
    }


    fun handleSelectedGenre(selectedPosition: Int) {
        val genreLList = genreListLiveData.value!!
        genreLList[selectedGenrePosition].isSelected = false
        genreLList[selectedPosition].isSelected = true
        selectedGenrePosition = selectedPosition
        handleShowData(localMoviesList)
        genreListLiveData.value = genreLList
    }
}
