package com.hugomora.upaxtmdbhugomora.helpers

import android.app.Application

// All global variables must be here to centralized all future changes
object SystemConst {
    // Enum classes
    enum class MainViews {
        Media, LocationsRecord, PicsUploaded
    }

    // Database names
    const val LOCATION_RECORD_DATABASE = "location_record_database"
    const val MOVIE_DATABASE = "movie_database"

    // Request codes
    const val PICK_IMAGE = 100
    const val REQUEST_LOCATION_PERMISSION = 200


    // Global variables to be used
    const val MOVIE_STARTING_PAGE_INDEX = 1
    const val NETWORK_PAGE_SIZE = 25
    const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"
}