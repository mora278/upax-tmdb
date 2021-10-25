package com.hugomora.upaxtmdbhugomora.ui.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel

class LocationsRecordDiffCallback(private val oldList: List<LocationRecordModel>, private val newList: List<LocationRecordModel>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}