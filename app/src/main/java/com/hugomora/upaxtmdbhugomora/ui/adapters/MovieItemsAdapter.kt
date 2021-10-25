package com.hugomora.upaxtmdbhugomora.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hugomora.upaxtmdbhugomora.R
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel
import com.hugomora.upaxtmdbhugomora.ui.adapters.diffutils.LocationsRecordDiffCallback
import com.hugomora.upaxtmdbhugomora.ui.adapters.diffutils.MoviesDiffCallback

class MovieItemsAdapter(private val context: Context, private val moviesList: MutableList<MovieModel>): RecyclerView.Adapter<MovieItemsAdapter.MovieItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItem =
        MovieItem(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false))

    override fun getItemCount(): Int = moviesList.count()

    override fun onBindViewHolder(holder: MovieItem, position: Int) {
        moviesList.getOrNull(position)?.let { movie ->
            holder.title.text = movie.title
            holder.overview.text = movie.overview
            holder.releaseDate.text = movie.releaseDate
            holder.popularity.text = movie.popularity
        }
    }


    fun setData(newList: List<MovieModel>) {
        val diffCallback = MoviesDiffCallback(moviesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        moviesList.clear()
        moviesList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class MovieItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: TextView = itemView.findViewById(R.id.movie_image)
        val title: TextView = itemView.findViewById(R.id.movie_tile)
        val overview: TextView = itemView.findViewById(R.id.movie_over_view)
        val releaseDate: TextView = itemView.findViewById(R.id.movie_release_date)
        val popularity: TextView = itemView.findViewById(R.id.movie_popularity)
    }
}