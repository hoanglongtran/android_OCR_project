package com.example.capstoneproject.records

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.example.capstoneproject.data.source.local.RecordImageFileManager
import java.io.File
import java.io.IOException
import java.util.ArrayList

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {


    var photoFile: File? = null
    lateinit var photoUri: Uri

    init {
        recordsView.presenter = this
    }

    override fun start() {
        loadRecordImages()
    }

    override fun loadRecordImages() {

    }

    override fun addNewRecordImage(context: Context) {
        photoFile = try {
            // Create the File where the photo should go
            RecordImageFileManager.createImageFile(context)

        } catch (ex: IOException) {
            // Error occurred while creating the File
            null
        }
        recordsView.currentImagePath = RecordImageFileManager.currentImagePath
        createPhotoUri(photoFile, context)
        recordsView.showAddRecordImage(photoUri)

    }

    override fun listRecordImage(context: Context): ArrayList<String> {
        return RecordImageFileManager.listFile(context)
    }

    private fun createPhotoUri(photoFile: File?, context: Context) {
        // Continue only if the File was successfully created
        photoFile?.also {
            photoUri = FileProvider.getUriForFile(
                context,
                "com.example.android.fileprovider",
                it
            )
            Log.d(TAG, photoUri.toString())
        }
    }



    companion object {

        private const val TAG = "RecordsPresenter"

    }
}