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
import com.hugomora.upaxtmdbhugomora.ui.adapters.diffutils.LocationsRecordDiffCallback

class LocationsRecordItemsAdapter(private val context: Context, private val locationsRecordList: MutableList<LocationRecordModel>, private val listener: LocationsRecordItemsListener): RecyclerView.Adapter<LocationsRecordItemsAdapter.LocationItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationItem =
        LocationItem(LayoutInflater.from(parent.context).inflate(R.layout.list_item_location_record, parent, false))

    override fun getItemCount(): Int = locationsRecordList.count()

    override fun onBindViewHolder(holder: LocationItem, position: Int) {
        locationsRecordList.getOrNull(position)?.let { locationRecord ->
            holder.itemView.setOnClickListener { listener.onClick(locationRecord) }
            holder.placeText.text = context.getString(R.string.location_place, locationRecord.latitude, locationRecord.longitude)
            holder.timeText.text = context.getString(R.string.location_time, locationRecord.timeSaved)
        }
    }

    fun setData(newList: List<LocationRecordModel>) {
        val diffCallback = LocationsRecordDiffCallback(locationsRecordList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        locationsRecordList.clear()
        locationsRecordList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    class LocationItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val placeText: TextView = itemView.findViewById(R.id.location_place_text)
        val timeText: TextView = itemView.findViewById(R.id.location_time_text)
    }

    interface LocationsRecordItemsListener {
        fun onClick(locationRecord: LocationRecordModel)
    }
}