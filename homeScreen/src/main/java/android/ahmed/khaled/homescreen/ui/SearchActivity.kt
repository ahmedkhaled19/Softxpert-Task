package android.ahmed.khaled.homescreen.ui

import android.ahmed.khaled.core.bases.BaseActivity
import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.homescreen.databinding.ActivitySearchBinding
import android.ahmed.khaled.homescreen.view_model.SearchViewModel
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activitySearchToolbar)
        handleSearchView()
        handleClickListeners()
    }

    private fun handleClickListeners() {
        binding.activitySearchBackBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleSearchView() {
        binding.activitySearchSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.trim().isEmpty()) return false

                viewModel.startSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    fun setHomeTitle(title: String) {
        binding.activitySearchTitleTv.text = title
    }

    fun showTitleView(isShow: Boolean) {
        binding.activitySearchTitleTv.isVisible = isShow
    }

    fun showSearchView(isShow: Boolean) {
        binding.activitySearchSearchView.isVisible = isShow
    }

    override fun getBaseViewModel(): BaseViewModel = viewModel

    override fun getActivityBinding(): View = binding.root
}