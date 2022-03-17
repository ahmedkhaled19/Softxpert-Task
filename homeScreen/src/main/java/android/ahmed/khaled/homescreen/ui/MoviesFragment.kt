package android.ahmed.khaled.homescreen.ui

import android.ahmed.khaled.core.R
import android.ahmed.khaled.core.bases.BaseFragment
import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.bases.PaginationScrollListener
import android.ahmed.khaled.core.utils.Constants.GRID_SPAN_COUNT
import android.ahmed.khaled.core.utils.Constants.HOME_DESTINATION
import android.ahmed.khaled.core.utils.UiUtils
import android.ahmed.khaled.homescreen.adapters.GenreAdapter
import android.ahmed.khaled.homescreen.adapters.MoviesAdapter
import android.ahmed.khaled.homescreen.databinding.FragmentMoviesBinding
import android.ahmed.khaled.homescreen.view_model.MoviesViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

@AndroidEntryPoint
class MoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel by viewModels<MoviesViewModel>()
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater)
        setupObservers()
        (requireActivity() as HomeActivity).setHomeTitle(getString(R.string.title))
        (requireActivity() as HomeActivity).showBackButton(false)
        (requireActivity() as HomeActivity).showSearchButton(true)
        return binding.root
    }

    private fun setupObservers() {
        with(viewModel) {
            showMessageWithAction.observe(viewLifecycleOwner) {
                UiUtils.showSnackBarWithAction(
                    getActivityBinding(), it.getMessage(requireContext())
                ) {
                    viewModel.loadData()
                }
            }

            showLoadingProgressBar.observe(viewLifecycleOwner) {
                binding.fragmentMoviesLoadingProgress.isVisible = it
            }

            showLoadingMoreProgressBar.observe(viewLifecycleOwner) {
                binding.fragmentMoviesLoadMoreProgress.isVisible = it
            }

            genreListLiveData.observe(viewLifecycleOwner) {
                binding.fragmentMoviesGenresRecyclerView.isVisible = true
                genreAdapter.addToList(it)
            }

            moviesListLiveData.observe(viewLifecycleOwner) {
                binding.fragmentMoviesRecyclerView.isVisible = true
                moviesAdapter.addToList(it)
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGenreAdapter()
        initMoviesAdapter()
        setupRecyclerView()

        if (viewModel.page == 0) {
            binding.fragmentMoviesGenresRecyclerView.isVisible = false
            binding.fragmentMoviesRecyclerView.isVisible = false
            binding.fragmentMoviesLoadingProgress.isVisible = true
            viewModel.loadData()
        }
    }

    private fun initGenreAdapter() {
        if (this::genreAdapter.isInitialized) return

        genreAdapter = GenreAdapter {
            if (viewModel.selectedGenrePosition != it) {
                moviesAdapter.clear()
                genreAdapter.clear()
                viewModel.handleSelectedGenre(it)
                scrollRecyclerToPosition(it)
            }
        }
    }

    private fun scrollRecyclerToPosition(position: Int) {
        binding.fragmentMoviesGenresRecyclerView.post {
            binding.fragmentMoviesGenresRecyclerView.smoothScrollToPosition(position)
        }
        binding.fragmentMoviesRecyclerView.post {
            binding.fragmentMoviesRecyclerView.smoothScrollToPosition(0)
        }
    }

    private fun initMoviesAdapter() {
        if (this::moviesAdapter.isInitialized) return

        moviesAdapter = MoviesAdapter {
            findNavController().navigate(
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                    it,
                    HOME_DESTINATION
                )
            )
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentMoviesGenresRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = genreAdapter
        }

        binding.fragmentMoviesRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
            adapter = moviesAdapter
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.isLoading = true
                    viewModel.loadData()
                }

                override fun isLastPage(): Boolean = viewModel.isLastPage

                override fun isLoading(): Boolean = viewModel.isLoading

            })
        }
    }

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun getActivityBinding(): View = binding.root

}