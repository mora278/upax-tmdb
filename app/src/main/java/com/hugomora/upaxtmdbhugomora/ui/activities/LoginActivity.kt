package com.hugomora.upaxtmdbhugomora.ui.activities

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.hugomora.upaxtmdbhugomora.R
import com.hugomora.upaxtmdbhugomora.databinding.ActivityLoginBinding
import com.hugomora.upaxtmdbhugomora.helpers.GeneralSystemHelper
import com.hugomora.upaxtmdbhugomora.helpers.SystemConst

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPrincipalFunctions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            SystemConst.REQUEST_LOCATION_PERMISSION -> {
                val isPermissionGranted =
                    grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && GeneralSystemHelper.isLocationPermissionEnable()
                if (isPermissionGranted) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }

    // Any indispensable function should be here
    private fun initPrincipalFunctions() {
        GeneralSystemHelper.application = application
        binding.startButton.setOnClickListener {
            if (GeneralSystemHelper.isLocationPermissionEnable()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                showLocationPermissionDialog()
            }
        }
    }

    private fun showLocationPermissionDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.location_permission_title))
            .setMessage(getString(R.string.location_permission_subtitle))
            .setPositiveButton(getString(R.string.sure)
            ) { _, _ ->
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), SystemConst.REQUEST_LOCATION_PERMISSION)
            }
            .create()
            .show()
    }
}