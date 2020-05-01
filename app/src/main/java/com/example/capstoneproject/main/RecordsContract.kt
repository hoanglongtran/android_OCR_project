package com.example.capstoneproject.main

import android.content.Context
import android.net.Uri
import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView
import java.io.File
import java.net.URI

/**
 * This specifies the contract between the view and the presenter.
 */
interface RecordsContract {

    interface View: BaseView<Presenter> {
        var currentPhotoPath: String

        var isActive: Boolean

        fun showRecord()

        fun showAddRecord(photoUri: Uri)

        fun showEditRecordImage(photoUri: Uri)
    }

    interface Presenter : BasePresenter {
        fun loadRecords()

        fun addNewRecord(context: Context)

        fun openRecord()

        fun deleteRecord()

        fun editRecordImage(context: Context)
    }
}