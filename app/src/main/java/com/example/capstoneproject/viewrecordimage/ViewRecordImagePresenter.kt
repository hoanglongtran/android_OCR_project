package com.example.capstoneproject.viewrecordimage

import android.R.id
import android.content.Context
import android.net.Uri
import android.util.Log
import java.io.File


class ViewRecordImagePresenter(val imagePath: String, val imagePosition: Int, val viewRecordImageView: ViewRecordImageContract.View): ViewRecordImageContract.Presenter
{
    init {
        viewRecordImageView.presenter = this
    }

    override fun editRecordImage(context: Context){
        Log.d(TAG,"editing picture")
        val uri = Uri.fromFile(File((imagePath)))
        viewRecordImageView.showEditRecordImage(uri)


    }

    override fun deleteImage() {
        val file = File(imagePath)
        val deleted: Boolean = file.delete()
        viewRecordImageView.showRecrodImageDeleted(imagePosition)
    }

    override fun inferImage() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Do something")
    }

    override fun getRecordImage(): File {
        return File(imagePath)
    }

    companion object{
        private const val TAG = "RecordImageView"
    }
}