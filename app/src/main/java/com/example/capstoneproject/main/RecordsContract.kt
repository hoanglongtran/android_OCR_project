package com.example.capstoneproject.main

import android.content.Context
import android.net.Uri
import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView
import java.io.File
import java.net.URI
import java.util.ArrayList

/**
 * This specifies the contract between the view and the presenter.
 */
interface RecordsContract {

    interface View: BaseView<Presenter> {
        var currentImagePath: String

        var isActive: Boolean

        fun showRecord()

        fun showAddRecordImage(photoUri: Uri)

        fun showEditRecordImage(photoUri: Uri)

        fun showInferRecordImage()
    }

    interface Presenter : BasePresenter {
        fun createImageData(uri: Uri, context: Context)

        fun loadRecordImages()

        fun addNewRecordImage(context: Context)

        fun openRecord()

        fun deleteRecord()

        fun editRecordImage(context: Context)

        fun inferRecordImage(context: Context)
        fun listRecordImage(context: Context): ArrayList<String>
    }
}