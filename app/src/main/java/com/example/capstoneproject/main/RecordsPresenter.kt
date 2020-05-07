package com.example.capstoneproject.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.example.capstoneproject.data.source.local.RecordImageFileManager
import java.io.File
import java.io.IOException

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

    override fun editRecordImage(context: Context){
        Log.d(TAG,"editing picture")

        recordsView.showEditRecordImage(photoUri)


    }

    override fun openRecord() {
        TODO("Not yet implemented")
    }

    override fun deleteRecord() {
        TODO("Not yet implemented")
    }

    override fun inferRecordImage() {
        recordsView.showInferRecordImage()
    }

    companion object {

        private const val TAG = "RecordsPresenter"

    }
}