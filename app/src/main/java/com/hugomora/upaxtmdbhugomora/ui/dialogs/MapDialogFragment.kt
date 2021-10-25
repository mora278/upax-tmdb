package com.hugomora.upaxtmdbhugomora.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hugomora.upaxtmdbhugomora.R
import com.hugomora.upaxtmdbhugomora.databinding.DialogFragmentMapBinding

class MapDialogFragment: DialogFragment() {
    private lateinit var binding: DialogFragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.DialogFullScreenTheme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogFragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrincipalFunctions()
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        binding.mapView.getMapAsync {
            it.addMarker(
                MarkerOptions()
                    .position(LatLng(getLatitude(), getLongitude()))
                    .title("Marker")
            )
            binding.mapView.onResume()
        }
    }

    private fun getDate(): String = arguments?.getString(DATE) ?: ""
    private fun getLatitude(): Double = arguments?.getDouble(LATITUDE) ?: 0.0
    private fun getLongitude(): Double = arguments?.getDouble(LONGITUDE) ?: 0.0

    companion object {
        const val TAG = "MAP_DIALOG_FRAGMENT_TAG"
        private const val DATE = "DATE"
        private const val LATITUDE = "LATITUDE"
        private const val LONGITUDE = "LONGITUDE"

        fun newInstance(date: String, latitude: Double, longitude: Double): MapDialogFragment {
            return MapDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(DATE, date)
                    putDouble(LATITUDE, latitude)
                    putDouble(LONGITUDE, longitude)
                }
            }
        }
    }
}