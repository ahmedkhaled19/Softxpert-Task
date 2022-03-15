package android.ahmed.khaled.homescreen.adapters

import android.ahmed.khaled.core.bases.BaseRecyclerViewAdapter
import android.ahmed.khaled.entities.local.Movie
import android.ahmed.khaled.homescreen.R
import android.ahmed.khaled.homescreen.databinding.ItemMovieListBinding
import android.view.View
import com.bumptech.glide.Glide

/**
 * Created by Ahmed Khaled on 15/03/2022.
 */

class MoviesAdapter(val onMovieSelect: ((selectedMovie: Movie) -> Unit)) :
    BaseRecyclerViewAdapter<Movie>({ oldItem, newItem -> oldItem.externalId == newItem.externalId }) {

    private lateinit var binding: ItemMovieListBinding

    override val itemLayoutRes: Int
        get() = R.layout.item_movie_list

    override fun bind(view: View, item: Movie, position: Int) {
        binding = ItemMovieListBinding.bind(view)

        Glide.with(view.context)
            .load(item.posterPath)
            .into(binding.itemMoviePoster)

        binding.itemMovieName.text = item.movieTitle
        binding.itemMovieDate.text = item.releaseDate

        view.setOnClickListener {
            onMovieSelect(item)
        }
    }
}