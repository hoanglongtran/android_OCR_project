package com.example.capstoneproject.main

import android.content.Context
import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView
import java.io.File

/**
 * This specifies the contract between the view and the presenter.
 */
interface RecordsContract {

    interface View: BaseView<Presenter> {
        var currentPhotoPath: String

        var isActive: Boolean

        fun showRecord()

        fun showAddRecord(photoFile: File?)

        fun showEditRecordImage()

    }

    interface Presenter : BasePresenter {
        fun loadRecords()

        fun addNewRecord(context: Context)

        fun openRecord()

        fun deleteRecord()
    }
}