package com.hugomora.upaxtmdbhugomora.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.LOCATION_RECORD_DATABASE

@Entity(tableName = LOCATION_RECORD_DATABASE)
data class LocationRecordModel(
    @PrimaryKey var timeSaved: String,
    @ColumnInfo(name = "latitude") var latitude: Double,
    @ColumnInfo(name = "longitude") var longitude: Double
)