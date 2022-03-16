package android.ahmed.khaled.core.bases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors

/**
 * Created by Ahmed Khaled on 16/08/2021.
 */

/**
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param T Type of the items in the list
 * */

abstract class BaseRecyclerViewAdapter<T>(
    areItemsTheSame: (T, T) -> Boolean,
    areItemsContentsTheSame: (T, T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
) : ListAdapter<T, BaseRecyclerViewAdapter.BaseViewHolder>(
    AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) = areItemsTheSame(oldItem, newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T) =
            areItemsContentsTheSame(oldItem, newItem)
    }).setBackgroundThreadExecutor(Executors.newFixedThreadPool(3)).build()
) {

    abstract val itemLayoutRes: Int
    protected abstract fun bind(view: View, item: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayoutRes, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder.itemView, getItem(position), position)
    }


    fun addToList(newList: Collection<T>) {
        submitList(ArrayList(currentList).apply { addAll(newList) })
    }

    fun clear() {
        submitList(null)
    }

    class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

}