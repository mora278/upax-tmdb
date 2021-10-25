package com.hugomora.upaxtmdbhugomora.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst.MainViews
import com.hugomora.upaxtmdbhugomora.ui.fragments.LocationsRecordFragment
import com.hugomora.upaxtmdbhugomora.ui.fragments.MediaFragment
import com.hugomora.upaxtmdbhugomora.ui.fragments.PicsUploadedFragment

class MainViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = MainViews.values().size

    override fun createFragment(position: Int): Fragment {
        return when (MainViews.values()[position]) {
            MainViews.Media -> MediaFragment.newInstance()
            MainViews.LocationsRecord -> LocationsRecordFragment.newInstance()
            MainViews.PicsUploaded -> PicsUploadedFragment.newInstance()
        }
    }
}