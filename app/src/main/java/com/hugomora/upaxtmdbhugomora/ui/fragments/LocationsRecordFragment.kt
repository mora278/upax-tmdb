package com.hugomora.upaxtmdbhugomora.ui.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hugomora.upaxtmdbhugomora.databinding.FragmentLocationsRecordBinding
import com.hugomora.upaxtmdbhugomora.data.models.LocationRecordModel
import com.hugomora.upaxtmdbhugomora.helpers.GeneralSystemHelper
import com.hugomora.upaxtmdbhugomora.helpers.ViewStatesHelper
import com.hugomora.upaxtmdbhugomora.ui.adapters.LocationsRecordItemsAdapter
import com.hugomora.upaxtmdbhugomora.ui.adapters.LocationsRecordItemsAdapter.LocationsRecordItemsListener
import com.hugomora.upaxtmdbhugomora.ui.dialogs.MapDialogFragment
import com.hugomora.upaxtmdbhugomora.viewmodels.LocationsRecordViewModel

class LocationsRecordFragment: Fragment(), LocationsRecordItemsListener {
    private lateinit var binding: FragmentLocationsRecordBinding

    private val locationsRecordViewModel: LocationsRecordViewModel by viewModels()

    private var locationsRecordItemsAdapter: LocationsRecordItemsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLocationsRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrincipalFunctions()
    }

    override fun onResume() {
        super.onResume()
        locationsRecordViewModel.loadData()
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        ViewStatesHelper.showLoadingState(binding)
        locationsRecordViewModel.getLocationsRecordObject().observe(viewLifecycleOwner, { setData(it) })
        context?.let {
            binding.recyclerView.layoutManager = LinearLayoutManager(it)
            locationsRecordItemsAdapter = LocationsRecordItemsAdapter(it, ArrayList(), this)
        }
        locationsRecordItemsAdapter?.let { binding.recyclerView.adapter = it }
    }

    private fun setData(listUpdated: List<LocationRecordModel>) {
        val newList = ArrayList<LocationRecordModel>().apply { addAll(listUpdated) }
        if (newList.isEmpty()) {
            ViewStatesHelper.showEmptyState(binding)
        } else {
            ViewStatesHelper.showContentState(binding)
            locationsRecordItemsAdapter?.setData(newList)
        }
    }

    private fun showMapDialog(locationRecord: LocationRecordModel) {
        activity?.supportFragmentManager?.let { fragmentManager ->
            val mapDialogFragment = MapDialogFragment.newInstance(locationRecord.timeSaved, locationRecord.latitude, locationRecord.longitude)
            fragmentManager.beginTransaction().add(mapDialogFragment, MapDialogFragment.TAG).commitAllowingStateLoss()
        }
    }

    companion object {
        fun newInstance(): LocationsRecordFragment {
            return LocationsRecordFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override fun onClick(locationRecord: LocationRecordModel) {
        showMapDialog(locationRecord)
    }
}