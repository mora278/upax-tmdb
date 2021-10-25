package com.hugomora.upaxtmdbhugomora.helpers

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object LocationsRecordUtils {

    fun getTimeForNewLocation(): String {
        val currentTime = Calendar.getInstance().time
        return SimpleDateFormat(SystemConst.SIMPLE_DATE_FORMAT, Locale("en")).format(currentTime)
    }

    fun getRemainingTimeToSaveLocation(): Int {
        val lastTimeSyncString = GeneralSystemHelper.getPreferences().lastTimeSync
        val currentTime = Calendar.getInstance().time
        return if (lastTimeSyncString.isNotBlank()) {
            val diff = currentTime.time - getCalendarInstanceFromTime(lastTimeSyncString).time.time
            val minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(diff).toInt()
            if (minutesRemaining > 30) 0 else minutesRemaining
        } else {
            0
        }
    }

    private fun getCalendarInstanceFromTime(timeSaved: String): Calendar {
        val dateAndTime = timeSaved.split(" ")
        val dateFormat = dateAndTime[0].split("-")
        val timeFormat = dateAndTime[1].split(":")
        val day = dateFormat[0].toInt()
        val month = dateFormat[1].toInt() - 1
        val year = dateFormat[2].toInt()
        val hour = timeFormat[0].toInt()
        val minutes = timeFormat[1].toInt()
        val seconds = timeFormat[2].toInt()
        val calendar = Calendar.getInstance(Locale.getDefault()).apply {
            set(year, month, day, hour, minutes, seconds)
        }
        return calendar
    }
}