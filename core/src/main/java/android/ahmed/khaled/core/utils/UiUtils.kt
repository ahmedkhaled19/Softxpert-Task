package android.ahmed.khaled.core.utils

import android.ahmed.khaled.core.R
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Ahmed Khaled on 11/12/20.
 */

object UiUtils {

    fun showSnackBar(view: View, msg: String?) {
        val snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        sbView.setBackgroundColor(
            ContextCompat.getColor(view.context, R.color.black)
        )
        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        textView.setTextColor(ContextCompat.getColor(view.context, R.color.white))
        snackbar.duration = 3000
        snackbar.show()
    }

    fun showSnackBarWithAction(
        view: View, msg: String?, listener: View.OnClickListener?
    ) {
        val snackbar = Snackbar.make(view, msg!!, Snackbar.LENGTH_INDEFINITE)
        val sbView = snackbar.view
        sbView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.black))
        val textView = sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        snackbar.setActionTextColor(Color.RED)
        snackbar.setAction(view.context.getString(R.string.retry), listener)
        snackbar.show()
    }

    fun hideKeyBoard(context: Context, view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}