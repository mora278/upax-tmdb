package com.hugomora.upaxtmdbhugomora.data.repositories

import androidx.room.Room
import com.hugomora.upaxtmdbhugomora.data.databases.LocationRecordDatabase
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.helpers.GeneralSystemHelper
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.LOCATION_RECORD_DATABASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationRecordRepository {
    private val database = Room.databaseBuilder(
        GeneralSystemHelper.application,
        LocationRecordDatabase::class.java,
        LOCATION_RECORD_DATABASE
    ).build()

    suspend fun addLocationRecords(vararg locationRecords: LocationRecordModel) = withContext(Dispatchers.IO) { database.locationRecordDao().insertAll(*locationRecords) }

    suspend fun getAllData(): List<LocationRecordModel> = withContext(Dispatchers.IO) { database.locationRecordDao().getAllData() }
}