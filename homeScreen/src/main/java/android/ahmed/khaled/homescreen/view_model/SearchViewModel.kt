package android.ahmed.khaled.homescreen.view_model

import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.common.SingleLiveEvent
import android.ahmed.khaled.domain.usecases.SearchRemoteUseCase
import android.ahmed.khaled.entities.local.Movie
import android.ahmed.khaled.entities.remote.SearchWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Ahmed Khaled on 17/03/2022.
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRemoteUseCase: SearchRemoteUseCase
) : BaseViewModel() {

    var searchText: String = ""

    val moviesListLiveData: SingleLiveEvent<MutableList<Movie>> by lazy {
        SingleLiveEvent()
    }

    val clearListLiveData: SingleLiveEvent<Unit> by lazy {
        SingleLiveEvent()
    }

    var page: Int = 0
    var isLoading: Boolean = false
    var isLastPage: Boolean = false

    fun startSearch(searchQuery: String) {
        if (searchQuery == searchText && isLastPage) return

        if (searchQuery != searchText && searchText.isNotEmpty()) {
            page = 0
            isLastPage = false
            clearListLiveData.value = Unit
        }

        searchText = searchQuery
        page++
        searchRemoteUseCase.sendRequest(SearchWrapper(page, searchQuery)) {
            onComplete { moviesList ->
                if (moviesList.isEmpty()) {
                    isLastPage = true
                    return@onComplete
                }
                moviesListLiveData.value = moviesList
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
}