package com.example.capstoneproject.main

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.capstoneproject.data.source.local.RecordImageFileManager
import com.example.capstoneproject.data.source.remote.FileDataPart
import com.example.capstoneproject.data.source.remote.VolleyFileUploadRequest
import java.io.File
import java.io.IOException

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {

    private var imageData: ByteArray? = null
    private val postURL: String = "https://ptsv2.com/t/ym5xv-1588907040/post" // remember to use your own api

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

    override fun inferRecordImage(context: Context) {
        recordsView.showInferRecordImage()
        uploadImage(context)
    }

    private fun uploadImage(context: Context) {
        Log.d(TAG, imageData.toString())
        imageData?: return
        val request = object : VolleyFileUploadRequest(
            Request.Method.POST,
            postURL,
            Response.Listener {
                println("response is: $it")
            },
            Response.ErrorListener {
                println("error is: $it")
            }
        ) {
            override fun getByteData(): MutableMap<String, FileDataPart> {
                var params = HashMap<String, FileDataPart>()
                params["imageFile"] = FileDataPart("image", imageData!!, "png")
                return params
            }
        }
        Volley.newRequestQueue(context).add(request)
    }

    @Throws(IOException::class)
    override fun createImageData(uri: Uri, context: Context) {
        val inputStream = context.contentResolver.openInputStream(uri)
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }

    companion object {

        private const val TAG = "RecordsPresenter"

    }
}