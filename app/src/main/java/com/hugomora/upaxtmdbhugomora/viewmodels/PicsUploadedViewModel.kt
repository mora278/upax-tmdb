package com.hugomora.upaxtmdbhugomora.viewmodels

import androidx.lifecycle.ViewModel
import com.hugomora.upaxtmdbhugomora.helpers.FirebaseStorageHelper
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.MediaStore

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData


class PicsUploadedViewModel: ViewModel() {
    private val firebaseStorageHelper: FirebaseStorageHelper = FirebaseStorageHelper()

    private var _picsList: MutableLiveData<List<String>> = MutableLiveData()
    fun setPicsList(newList: List<String>) = _picsList.postValue(newList)
    fun getPicsList() = _picsList.value ?: emptyList()
    fun getPicsListObject() = _picsList

}