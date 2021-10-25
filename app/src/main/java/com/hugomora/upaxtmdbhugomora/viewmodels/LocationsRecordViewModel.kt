package com.hugomora.upaxtmdbhugomora.viewmodels

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.data.repositories.LocationRecordRepository
import com.hugomora.upaxtmdbhugomora.helpers.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class LocationsRecordViewModel: ViewModel() {

    private val firestore = FirestoreHelper()
    private val locationRecordDatabase = LocationRecordRepository()
    private val timer = Timer()
    private var timerTask: TimerTask? = null
    private var locationManager: LocationManager? = null


    private val _locationsRecordList = MutableLiveData<List<LocationRecordModel>>()
    private fun setLocationsRecordList(newList: List<LocationRecordModel>) = _locationsRecordList.postValue(newList)
    fun getLocationsRecordList() = _locationsRecordList.value ?: emptyList()
    fun getLocationsRecordObject() = _locationsRecordList

    init {
        startTimerTask()
    }

    fun loadData() = viewModelScope.launch(Dispatchers.Main) {
        val newList = locationRecordDatabase.getAllData()
        delay(2000)
        setLocationsRecordList(newList)
    }

    // Will start timer to save the location record
    private fun startTimerTask() {
        var remainingTime = LocationsRecordUtils.getRemainingTimeToSaveLocation()
        val minutes: Int = if (remainingTime == 0) {
            GeneralSystemHelper.getPreferences().lastTimeSync = LocationsRecordUtils.getTimeForNewLocation()
            1000 * 60 * 30
        } else {
            remainingTime
        }
        timerTask = object: TimerTask() {
            override fun run() {
                addLocationRecord()
            }
        }
        timerTask?.let { timer.schedule(timerTask, 0L, minutes.toLong()) }
    }

    private fun addLocationRecord() = viewModelScope.launch(Dispatchers.Main) {
        locationRecordDatabase.addLocationRecords(getNewLocationRecordObject())
    }

    // get last location known
    private fun getLocation(): Location? {
        if ((ActivityCompat.checkSelfPermission(GeneralSystemHelper.application, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GeneralSystemHelper.application, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED).not()) {
            locationManager = (GeneralSystemHelper.application.getSystemService(LOCATION_SERVICE) as? LocationManager)
            return locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        }
        return null
    }

    // will create new object to be saved
    private fun getNewLocationRecordObject(): LocationRecordModel {
        return LocationRecordModel(
            timeSaved = LocationsRecordUtils.getTimeForNewLocation(),
            latitude =  getLocation()?.latitude ?: 0.0,
            longitude = getLocation()?.longitude ?: 0.0,
        )
    }
}