package android.ahmed.khaled.domain.mappers

import android.ahmed.khaled.core.utils.Constants.IMAGE_BASE_URL
import android.ahmed.khaled.domain.fixtures.MoviesFixture.remoteMovie
import android.ahmed.khaled.domain.fixtures.MoviesFixture.remoteMovieWithNullDate
import android.ahmed.khaled.domain.fixtures.MoviesFixture.remoteMovieWithNullTitle
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by Ahmed Khaled on 17/03/2022.
 */

class MovieMapperTest {

    private lateinit var mapper: MovieMapper

    @Before
    fun setUp() {
        mapper = MovieMapper()
    }

    @Test
    fun `convert remote movie response to local movie correctly`() {
        val localMovie = mapper.convert(remoteMovie)

        assertThat(localMovie.externalId).isEqualTo(remoteMovie.id)
        assertThat(localMovie.movieTitle).isEqualTo(remoteMovie.title)
        assertThat(localMovie.posterPath).isEqualTo("${IMAGE_BASE_URL}${remoteMovie.poster_path}")
        assertThat(localMovie.overview).isEqualTo(remoteMovie.overview)
        assertThat(localMovie.voteAverage).isEqualTo(remoteMovie.vote_average)
        assertThat(localMovie.releaseDate).isEqualTo(remoteMovie.release_date)
    }

    @Test
    fun `convert remote movie with null title to local movie correctly`() {
        val localMovie = mapper.convert(remoteMovieWithNullTitle)

        assertThat(localMovie.externalId).isEqualTo(remoteMovieWithNullTitle.id)
        assertThat(localMovie.movieTitle).isEqualTo("")
        assertThat(localMovie.posterPath).isEqualTo("${IMAGE_BASE_URL}${remoteMovieWithNullTitle.poster_path}")
        assertThat(localMovie.overview).isEqualTo(remoteMovieWithNullTitle.overview)
        assertThat(localMovie.voteAverage).isEqualTo(remoteMovieWithNullTitle.vote_average)
        assertThat(localMovie.releaseDate).isEqualTo(remoteMovieWithNullTitle.release_date)
    }

    @Test
    fun `convert remote movie with null date to local movie correctly`() {
        val localMovie = mapper.convert(remoteMovieWithNullDate)

        assertThat(localMovie.externalId).isEqualTo(remoteMovieWithNullDate.id)
        assertThat(localMovie.movieTitle).isEqualTo(remoteMovieWithNullDate.title)
        assertThat(localMovie.posterPath).isEqualTo("${IMAGE_BASE_URL}${remoteMovieWithNullDate.poster_path}")
        assertThat(localMovie.overview).isEqualTo(remoteMovieWithNullDate.overview)
        assertThat(localMovie.voteAverage).isEqualTo(remoteMovieWithNullDate.vote_average)
        assertThat(localMovie.releaseDate).isEqualTo("")
    }
}