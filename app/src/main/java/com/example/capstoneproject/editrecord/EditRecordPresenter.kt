package com.example.capstoneproject.editrecord

import android.content.Context
import android.net.Uri
import com.example.capstoneproject.data.source.remote.HTTPRequestManager
import org.json.JSONObject
import java.io.File
import java.io.IOException


class EditRecordPresenter(val imagePath: String, val editRecordView: EditRecordContract.View): EditRecordContract.Presenter
{
    init {
        editRecordView.presenter = this
    }

    override fun start() {
        TODO("Do something")
    }

    fun setContent(resultJSON: JSONObject){
        val firstColumn = resultJSON.getString("0")
        val secondColumn = resultJSON.getString("1")

        editRecordView.setContent(firstColumn, secondColumn)
    }

    override fun inferImage(context: Context) {
        uploadImage(context)

    }

    override fun uploadImage(context: Context) {
        val imageData: ByteArray? = createImageData(context)
        imageData?: return
        HTTPRequestManager.uploadImage(imageData, this)
    }

    @Throws(IOException::class)
    override fun createImageData(context: Context): ByteArray? {
        val inputStream = context.contentResolver.openInputStream(Uri.fromFile(File(imagePath)))
        inputStream?.buffered()?.use {
            return it.readBytes()
        }
        return null
    }


}