package com.hugomora.upaxtmdbhugomora.helpers

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager

class PreferencesHelper(private val context: Context): Application() {
    var uniqueId = "UNIQUE_ID"
        get() = PreferenceManager.getDefaultSharedPreferences(context).getString(field, "") ?: ""
        set(value) = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(field, value).apply()

    var lastTimeSync = "lastTimeSync"
        get() = PreferenceManager.getDefaultSharedPreferences(context).getString(field, "") ?: ""
        set(value) = PreferenceManager.getDefaultSharedPreferences(context).edit().putString(field, value).apply()
}