package com.hugomora.upaxtmdbhugomora.helpers

import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.Group
import com.hugomora.upaxtmdbhugomora.databinding.FragmentLocationsRecordBinding
import com.hugomora.upaxtmdbhugomora.databinding.FragmentMediaBinding
import com.hugomora.upaxtmdbhugomora.databinding.FragmentPicsUploadedBinding


// Object helper to reuse in different fragments if it is necessary
// and avoid duplicate code in classes, anyone can add new views
// just be sure to use the correct names and views

// The use of groups is to support different content views, no matters
// its content, it will always show the content of the view

object ViewStatesHelper {
    fun showLoadingState(binding: Any) {
        when (binding) {
            is FragmentLocationsRecordBinding   -> binding.apply { progressBar.show(); contentStateGroup.hide(); emptyStateGroup.hide() }
            is FragmentMediaBinding             -> binding.apply { progressBar.show(); contentStateGroup.hide(); emptyStateGroup.hide() }
            is FragmentPicsUploadedBinding      -> binding.apply { progressBar.show(); contentStateGroup.hide(); emptyStateGroup.hide() }
        }
    }

    fun showEmptyState(binding: Any) {
        when (binding) {
            is FragmentLocationsRecordBinding   -> binding.apply { progressBar.hide(); contentStateGroup.hide(); emptyStateGroup.show() }
            is FragmentMediaBinding             -> binding.apply { progressBar.hide(); contentStateGroup.hide(); emptyStateGroup.show() }
            is FragmentPicsUploadedBinding      -> binding.apply { progressBar.hide(); contentStateGroup.hide(); emptyStateGroup.show() }
        }
    }

    fun showContentState(binding: Any) {
        when (binding) {
            is FragmentLocationsRecordBinding   -> binding.apply { progressBar.hide(); contentStateGroup.show(); emptyStateGroup.hide() }
            is FragmentMediaBinding             -> binding.apply { progressBar.hide(); contentStateGroup.show(); emptyStateGroup.hide() }
            is FragmentPicsUploadedBinding      -> binding.apply { progressBar.hide(); contentStateGroup.show(); emptyStateGroup.hide() }
        }
    }

    // Views extensions to simplify code and do it more understandable

    private fun ProgressBar.show() { this.visibility = View.VISIBLE }
    private fun ProgressBar.hide() { this.visibility = View.GONE }

    private fun Group.show() { this.visibility = View.VISIBLE }
    private fun Group.hide() { this.visibility = View.GONE }
}