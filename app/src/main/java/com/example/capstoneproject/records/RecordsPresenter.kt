package com.example.capstoneproject.records

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import com.example.capstoneproject.data.source.local.RecordImageFileManager
import com.example.capstoneproject.data.source.remote.HTTPRequestManager
import java.io.File
import java.io.IOException
import java.util.ArrayList

class RecordsPresenter(val recordsView: RecordsContract.View): RecordsContract.Presenter {

    private var imageData: ByteArray? = null
    private val postURL: String = "http://10.0.2.2:5000/" // remember to use your own api

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
        imageData?: return
        val response = HTTPRequestManager.uploadImage(imageData!!)
        /*val request = object : VolleyFileUploadRequest(
            Method.POST,
            postURL,
            Response.Listener {
                val result: String = it.data.toString()

                var resultJSON: JSONObject = JSONObject(result)
                val status: String = resultJSON.getString("status")
                var sentences: String = resultJSON.getString("result")
                Log.i(TAG, "Result: $sentences")
                Log.i(TAG, "Status is: $status")
                //println("response is: $it")
            },
            Response.ErrorListener {
                Log.i(TAG, "error is: $it")
            }
        ) {
            override fun getByteData(): MutableMap<String, FileDataPart> {
                val params = HashMap<String, FileDataPart>()
                params["file"] = FileDataPart("image", imageData!!, "png")
                return params
            }


        }
        VolleyLog.DEBUG = true
        request.retryPolicy = DefaultRetryPolicy(
            30000000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        Log.i(TAG, request.toString())
        Volley.newRequestQueue(context).add(request)*/
    }

    @Throws(IOException::class)
    override fun createImageData(uri: Uri, context: Context) {
        val inputStream = context.contentResolver.openInputStream(uri) //Uri.parse("file:///storage/emulated/0/Android/data/com.example.capstoneproject/files/Pictures/21.png"))
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }

    companion object {

        private const val TAG = "RecordsPresenter"

    }
}