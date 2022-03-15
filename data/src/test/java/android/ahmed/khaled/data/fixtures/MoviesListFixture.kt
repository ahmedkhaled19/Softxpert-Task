package android.ahmed.khaled.data.fixtures

import android.ahmed.khaled.entities.remote.RemoteMovies

object MoviesListFixture {

    val remote = listOf(
        RemoteMovies(id = 278).apply {
            adult = false
            title = "The Shawshank Redemption"
            vote_average = 8.32
            overview =
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope."
            poster_path = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg"
            genre_ids = listOf(18, 80)
            release_date = "1994-09-10"
            original_title = "The Shawshank Redemption"
            original_language = "en"
        },
        RemoteMovies(id = 19404).apply {
            adult = false
            title = "Dilwale Dulhania Le Jayenge"
            vote_average = 8.7
            overview =
                "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity."
            poster_path = "/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg"
            genre_ids = listOf(35, 18, 10749)
            release_date = "1995-10-20"
            original_title = "दिलवाले दुल्हनिया ले जायेंगे"
            original_language = "hi"
        })
}