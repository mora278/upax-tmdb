package com.hugomora.upaxtmdbhugomora.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.hugomora.upaxtmdbhugomora.R
import com.hugomora.upaxtmdbhugomora.databinding.ActivityMainBinding
import com.hugomora.upaxtmdbhugomora.helpers.GeneralSystemHelper
import com.hugomora.upaxtmdbhugomora.helpers.PreferencesHelper
import com.hugomora.upaxtmdbhugomora.ui.adapters.MainViewPagerAdapter
import com.hugomora.upaxtmdbhugomora.viewmodels.MovieViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var mainViewPagerAdapter: MainViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPrincipalFunctions()
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        GeneralSystemHelper.saveUniqueIdIfNecessary()
        initAdapter()
    }

    private fun initAdapter() {
        mainViewPagerAdapter = MainViewPagerAdapter(this)
        mainViewPagerAdapter?.let {
            binding.viewPager.adapter = it
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.Media)
                    1 -> tab.text = getString(R.string.locations_record)
                    2 -> tab.text = getString(R.string.pics_uploaded)
                }
            }.attach()
        }
    }
}