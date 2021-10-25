package com.hugomora.upaxtmdbhugomora.data.dao

import androidx.room.*
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.LOCATION_RECORD_DATABASE

@Dao
interface LocationRecordDao {
    @Query("SELECT * FROM $LOCATION_RECORD_DATABASE")
    fun getAllData(): List<LocationRecordModel>

    @Insert
    fun insertAll(vararg locationRecords: LocationRecordModel)

    @Delete
    suspend fun delete(locationRecordModel: LocationRecordModel)

    @Update
    suspend fun updateLocationRecord(vararg locationRecords: LocationRecordModel)
}