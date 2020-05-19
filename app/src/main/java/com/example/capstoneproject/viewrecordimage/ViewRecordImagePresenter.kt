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

    }


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