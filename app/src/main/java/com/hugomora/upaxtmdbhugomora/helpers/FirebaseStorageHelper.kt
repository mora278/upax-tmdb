package com.hugomora.upaxtmdbhugomora.helpers

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class FirebaseStorageHelper {
    private var storageRef: StorageReference? = null

    init {
        val storage = FirebaseStorage.getInstance()
        storageRef = storage.reference
    }

    fun uploadPic(pathName: String) {
        val file = Uri.fromFile(File(pathName))
        val riversRef = storageRef?.child("images/${file.lastPathSegment}")
        val uploadTask = riversRef?.putFile(file)

        // Register observers to listen for when the download is done or if it fails
        uploadTask?.addOnFailureListener {
            // Handle unsuccessful uploads
        }?.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }

    }
}