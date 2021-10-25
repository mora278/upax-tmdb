package com.hugomora.upaxtmdbhugomora.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.google.android.gms.common.GoogleApiAvailabilityLight
import com.hugomora.upaxtmdbhugomora.databinding.FragmentPicsUploadedBinding
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst

class PicsUploadedFragment: Fragment() {
    private lateinit var binding: FragmentPicsUploadedBinding

    private val picsUploadedViewModel: ViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPicsUploadedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPrincipalFunctions()
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        binding.root.setOnClickListener { openGallery() }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        registerForActivityResult(StartActivityForResult()) { result ->

        }.launch(gallery)
    }

    private fun showCorrectViewState() {

    }

    companion object {
        fun newInstance(): PicsUploadedFragment {
            return PicsUploadedFragment().apply {
                arguments = Bundle()
            }
        }
    }
}