package android.ahmed.khaled.homescreen.ui

import android.ahmed.khaled.core.bases.BaseFragment
import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.utils.Constants
import android.ahmed.khaled.entities.local.Movie
import android.ahmed.khaled.homescreen.databinding.FragmentMovieDetailBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ahmed Khaled on 16/03/2022.
 */

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.apply {
            setData(movie)
            handleParentView(destination, movie.movieTitle)
        }
    }

    private fun handleParentView(destination: Int, movieTitle: String) {
        when (destination) {
            Constants.HOME_DESTINATION -> {
                (requireActivity() as HomeActivity).setHomeTitle(movieTitle)
                (requireActivity() as HomeActivity).showBackButton(true)
                (requireActivity() as HomeActivity).showSearchButton(false)
            }

            Constants.SEARCH_DESTINATION -> {
                (requireActivity() as SearchActivity).setHomeTitle(movieTitle)
                (requireActivity() as SearchActivity).showTitleView(true)
                (requireActivity() as SearchActivity).showSearchView(false)
            }
        }
    }

    private fun setData(movie: Movie) {
        Glide.with(requireContext())
            .load(movie.posterPath)
            .into(binding.fragmentMovieDetailImage)

        binding.fragmentMovieDetailName.text = movie.movieTitle
        binding.fragmentMovieDetailDate.text = movie.releaseDate
        binding.fragmentMovieDetailVoteAverage.text = movie.voteAverage.toString()
        binding.fragmentMovieDetailOverview.text = movie.overview
    }

    override fun getBaseViewModel(): BaseViewModel? = null

    override fun getActivityBinding(): View = binding.root
}