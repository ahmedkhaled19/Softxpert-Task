package android.ahmed.khaled.homescreen.ui

import android.ahmed.khaled.core.bases.BaseFragment
import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.core.bases.PaginationScrollListener
import android.ahmed.khaled.core.utils.Constants
import android.ahmed.khaled.homescreen.adapters.MoviesAdapter
import android.ahmed.khaled.homescreen.databinding.FragmentSearchBinding
import android.ahmed.khaled.homescreen.view_model.SearchViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

/**
 * Created by Ahmed Khaled on 17/03/2022.
 */

class SearchFragment : BaseFragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        setupObservers()
        (requireActivity() as SearchActivity).showTitleView(false)
        (requireActivity() as SearchActivity).showSearchView(true)
        return binding.root
    }

    private fun setupObservers() {
        with(viewModel) {
            showLoadingProgressBar.observe(viewLifecycleOwner) {
                binding.fragmentSearchLoadingProgress.isVisible = it
                binding.fragmentSearchRecyclerView.isVisible = !it
            }

            showLoadingMoreProgressBar.observe(viewLifecycleOwner) {
                binding.fragmentSearchLoadMoreProgress.isVisible = it
            }

            moviesListLiveData.observe(viewLifecycleOwner) {
                moviesAdapter.addToList(it)
            }

            clearListLiveData.observe(viewLifecycleOwner) {
                moviesAdapter.clear()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMoviesAdapter()
        setupRecyclerView()
    }

    private fun initMoviesAdapter() {
        if (this::moviesAdapter.isInitialized) return

        moviesAdapter = MoviesAdapter {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToMovieDetailFragment(
                    movie = it,
                    destination = Constants.SEARCH_DESTINATION
                )
            )
        }
    }

    private fun setupRecyclerView() {
        binding.fragmentSearchRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), Constants.GRID_SPAN_COUNT)
            adapter = moviesAdapter
            addOnScrollListener(object :
                PaginationScrollListener(layoutManager as GridLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.isLoading = true
                    viewModel.startSearch(viewModel.searchText)
                }

                override fun isLastPage(): Boolean = viewModel.isLastPage

                override fun isLoading(): Boolean = viewModel.isLoading

            })
        }
    }


    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun getActivityBinding(): View = binding.root
}