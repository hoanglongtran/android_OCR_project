package com.example.capstoneproject.main

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {

    init {
        recordsView.presenter = this
    }

    override fun start() {
        loadRecords()
    }

    override fun loadRecords() {

    }

    override fun addNewRecord(context: Context) {
        val photoFile: File? = try {
            createImageFile(context)
        } catch (ex: IOException) {
            // Error occurred while creating the File

            null
        }
        recordsView.showAddRecord(photoFile)
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
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "RECORD_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            recordsView.currentPhotoPath = absolutePath
        }
    }
}