package com.example.capstoneproject.viewrecordimage

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.capstoneproject.data.source.local.RecordImageFileManager
import com.example.capstoneproject.data.source.remote.HTTPRequestManager
import org.json.JSONObject
import java.io.File
import java.io.IOException


class ViewRecordImagePresenter(val imagePath: String, val imagePosition: Int, val viewRecordImageView: ViewRecordImageContract.View): ViewRecordImageContract.Presenter
{
    private var imageData: ByteArray? = null

    init {
        viewRecordImageView.presenter = this
    }

    override fun editRecordImage(context: Context){
        Log.d(TAG,"editing picture")
        val uri = Uri.fromFile(File((imagePath)))
        viewRecordImageView.showEditRecordImage(uri)


    }

    override fun deleteImage() {
        RecordImageFileManager.deleteImage(imagePath)
        viewRecordImageView.showRecrodImageDeleted(imagePosition)
    }

    override fun inferImage(context: Context) {
        viewRecordImageView.showInferRecordImage(imagePath)
        /*val resultJSON = uploadImage(context)
        val firstColumn = resultJSON?.getString("0")
        val secondColumn = resultJSON?.getString("1")*/


    }

/*    private fun uploadImage(context: Context): JSONObject? {
        createImageData(context)
        //imageData?: return
        return HTTPRequestManager.uploadImage(imageData!!)
    }

    @Throws(IOException::class)
    override fun createImageData(context: Context) {
        val inputStream = context.contentResolver.openInputStream(Uri.fromFile(File(imagePath)))
        inputStream?.buffered()?.use {
            imageData = it.readBytes()
        }
    }*/

    override fun start() {
        TODO("Do something")
    }

    override fun getRecordImage(): File {
        return File(imagePath)
    }

    companion object{
        private const val TAG = "ViewRecordImagePresenter"
    }
}