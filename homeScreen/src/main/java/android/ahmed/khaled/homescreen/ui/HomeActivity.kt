package android.ahmed.khaled.homescreen.ui

import android.ahmed.khaled.core.bases.BaseActivity
import android.ahmed.khaled.core.bases.BaseViewModel
import android.ahmed.khaled.homescreen.databinding.ActivityHomeBinding
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.activityHomeToolbar)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.activityHomeBackBtn.setOnClickListener {
            onBackPressed()
        }

        binding.activityHomeSearchIcon.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))

        }
    }

    fun setHomeTitle(title: String) {
        binding.activityHomeTitleTv.text = title
    }

    fun showBackButton(isShow: Boolean) {
        binding.activityHomeBackBtn.isVisible = isShow
    }

    fun showSearchButton(isShow: Boolean) {
        binding.activityHomeSearchIcon.isVisible = isShow
    }

    override fun getBaseViewModel(): BaseViewModel? = null

    override fun getActivityBinding(): View = binding.root
}