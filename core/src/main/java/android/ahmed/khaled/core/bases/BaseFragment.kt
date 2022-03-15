package android.ahmed.khaled.core.bases

import android.ahmed.khaled.core.utils.UiUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Ahmed Khaled on 18/08/2021.
 */

abstract class BaseFragment : Fragment() {

    private var baseViewModel: BaseViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        baseViewModel = getBaseViewModel()
        setupObserver()
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    private fun setupObserver() {
        if (baseViewModel == null) return

        with(baseViewModel!!) {
            showMessage.observe(viewLifecycleOwner) { messageWrapper ->
                UiUtils.showSnackBar(
                    getActivityBinding(),
                    messageWrapper.getMessage(requireActivity())
                )
            }
        }
    }

    abstract fun getBaseViewModel(): BaseViewModel?

    abstract fun getActivityBinding(): View
}