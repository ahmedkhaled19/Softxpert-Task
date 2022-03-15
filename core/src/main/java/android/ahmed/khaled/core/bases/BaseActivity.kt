package android.ahmed.khaled.core.bases

import android.ahmed.khaled.core.utils.UiUtils
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

/**
 * Created by Ahmed Khaled on 11/12/20.
 */

abstract class BaseActivity : AppCompatActivity() {

    private var baseViewModel: BaseViewModel? = null
    var loadingProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        baseViewModel = getBaseViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        if (baseViewModel == null) return

        with(baseViewModel!!) {
            showMessage.observe(this@BaseActivity) { messageWrapper ->
                UiUtils.showSnackBar(
                    getActivityBinding(),
                    messageWrapper.getMessage(this@BaseActivity)
                )
            }

            moveToIntent.observe(this@BaseActivity) { targetClass ->
                startActivity(Intent(this@BaseActivity, targetClass))
            }

            showLoadingProgressBar.observe(this@BaseActivity) { isShow ->
                if (loadingProgressBar == null) return@observe

                if (isShow) {
                    loadingProgressBar!!.visibility = VISIBLE
                } else {
                    loadingProgressBar!!.visibility = GONE

                }
            }
        }
    }

    abstract fun getBaseViewModel(): BaseViewModel?

    abstract fun getActivityBinding(): View
}