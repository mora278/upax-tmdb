package com.hugomora.upaxtmdbhugomora.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hugomora.upaxtmdbhugomora.data.dao.LocationRecordDao
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.LOCATION_RECORD_DATABASE

@Database(entities = [LocationRecordModel::class], version = 1)
abstract class LocationRecordDatabase: RoomDatabase() {
    abstract fun locationRecordDao(): LocationRecordDao

    companion object {
        @Volatile
        private var instance: LocationRecordDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            LocationRecordDatabase::class.java,
            LOCATION_RECORD_DATABASE
        ).build()
    }
}