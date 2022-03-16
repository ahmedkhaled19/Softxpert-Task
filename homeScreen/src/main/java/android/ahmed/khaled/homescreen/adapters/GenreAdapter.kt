package android.ahmed.khaled.homescreen.adapters

import android.ahmed.khaled.core.bases.BaseRecyclerViewAdapter
import android.ahmed.khaled.entities.local.Genre
import android.ahmed.khaled.homescreen.R
import android.ahmed.khaled.homescreen.databinding.ItemGenreListBinding
import android.view.View

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

class GenreAdapter(val onTypeSelected: ((position: Int) -> Unit)) :
    BaseRecyclerViewAdapter<Genre>({ oldItem, newItem -> oldItem.id == newItem.id }) {

    private lateinit var binding: ItemGenreListBinding

    override val itemLayoutRes: Int
        get() = R.layout.item_genre_list

    override fun bind(view: View, item: Genre, position: Int) {
        binding = ItemGenreListBinding.bind(view)
        binding.itemGenreListName.text = item.name
        view.setOnClickListener {
            onTypeSelected(position)
        }
    }
}