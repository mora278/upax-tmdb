package com.hugomora.upaxtmdbhugomora.helpers

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.*

object GeneralSystemHelper {
    lateinit var application: Application

    fun showShortMessage(message: String) = Toast.makeText(application, message, Toast.LENGTH_SHORT).show()

    fun getPreferences(): PreferencesHelper = PreferencesHelper(application)

    fun isLocationPermissionEnable(): Boolean = (ContextCompat.checkSelfPermission(application, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)

    fun saveUniqueIdIfNecessary() {
        if (getPreferences().uniqueId.isBlank()) {
            getPreferences().uniqueId = UUID.randomUUID().toString()
        }
    }
}