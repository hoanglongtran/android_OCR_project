package com.example.capstoneproject.main

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {

    var photoFile: File? = null
    lateinit var photoUri: Uri

    init {
        recordsView.presenter = this
    }

    override fun start() {
        loadRecords()
    }

    override fun loadRecords() {

    }

    override fun addNewRecord(context: Context) {
        photoFile = try {
            // Create the File where the photo should go
            createImageFile(context)
        } catch (ex: IOException) {
            // Error occurred while creating the File
            null
        }
        createPhotoUri(photoFile, context)
        recordsView.showAddRecord(photoUri)

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

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "RECORD_${timeStamp}_", /* prefix */
            ".png", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            recordsView.currentPhotoPath = absolutePath
        }
    }

    companion object {

        private const val TAG = "RecordsPresenter"

    }
}