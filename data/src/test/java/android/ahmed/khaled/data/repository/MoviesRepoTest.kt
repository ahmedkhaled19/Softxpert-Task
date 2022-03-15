package android.ahmed.khaled.data.repository

import android.ahmed.khaled.data.fixtures.MoviesListFixture
import android.ahmed.khaled.data.util.BaseRemoteDataSourceTest
import android.ahmed.khaled.data.util.enqueueResponse
import android.ahmed.khaled.entities.responses.MoviesResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

@ExperimentalCoroutinesApi
class MoviesRepoTest : BaseRemoteDataSourceTest(){

    private val expectedValue = MoviesResponse().apply {
        page = 1
        results = MoviesListFixture.remote
    }

    @Test
    fun `should fetch movies correctly given 200 response`() {
        runBlocking {
            mockWebServer.enqueueResponse("MoviesSuccessResponse.json", 200)
            val actualResults = moviesEndPoints.getTopRatedMovies()
            Truth.assertThat(actualResults.page).isEqualTo(expectedValue.page)
            Truth.assertThat(actualResults.results).containsExactlyElementsIn(expectedValue.results)
        }
    }
}