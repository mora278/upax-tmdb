package com.hugomora.upaxtmdbhugomora.ui.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.hugomora.upaxtmdbhugomora.data.models.MovieModel

class MoviesDiffCallback(private val oldList: List<MovieModel>, private val newList: List<MovieModel>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}