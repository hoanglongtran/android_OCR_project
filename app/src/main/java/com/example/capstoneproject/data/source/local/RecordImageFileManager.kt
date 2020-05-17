package com.example.capstoneproject.data.source.local

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


object RecordImageFileManager{

    var currentImagePath: String = ""

    // Create the File where the photo should go
    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "RECORD_${timeStamp}_", /* prefix */
            ".png", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentImagePath = absolutePath
        }
    }

    fun listFile(context: Context): ArrayList<String> {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val fileList = storageDir?.listFiles()

        return ArrayList<String>().apply {
            if (fileList != null && fileList.size > 0) {
                for (currentFile in fileList) {
                    if (currentFile.name.endsWith(".png") || currentFile.name.endsWith(".jpg")) {
                        // File absolute path
                        Log.d(TAG, currentFile.getAbsolutePath())
                        // File Name
                        Log.d(TAG, currentFile.getName())
                        add(currentFile.getAbsolutePath())
                    }
                }
                Log.d(TAG, "" + fileList.size)
            }
        }
    }


    private const val TAG = "ImageManager"

}