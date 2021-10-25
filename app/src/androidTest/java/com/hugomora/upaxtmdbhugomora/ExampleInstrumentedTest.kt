package com.hugomora.upaxtmdbhugomora

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hugomora.upaxtmdbhugomora.data.dao.LocationRecordDao
import com.hugomora.upaxtmdbhugomora.data.databases.LocationRecordDatabase
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var locationRecordDao: LocationRecordDao
    private lateinit var db: LocationRecordDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = LocationRecordDatabase.invoke(context)
        locationRecordDao = db.locationRecordDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        //db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val locationRecordModel = LocationRecordModel("title", 1.23, 4.56)
        locationRecordDao.insertAll(locationRecordModel)
        val locationRecordList = locationRecordDao.getAllData()
        MatcherAssert.assertThat(locationRecordList[0], CoreMatchers.equalTo(locationRecordModel))
    }
}