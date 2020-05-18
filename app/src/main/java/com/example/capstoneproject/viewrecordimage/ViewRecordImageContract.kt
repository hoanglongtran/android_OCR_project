package com.example.capstoneproject.viewrecordimage

import android.content.Context
import android.net.Uri
import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView
import java.io.File

interface ViewRecordImageContract {
    interface View: BaseView<Presenter> {

        var isActive: Boolean

        fun showEditRecordImage(photoUri: Uri)

        fun showRecrodImageDeleted(imagePosition: Int)

    }

    interface Presenter : BasePresenter {

        fun deleteImage()

        fun inferImage()

        fun getRecordImage(): File

        fun editRecordImage(context: Context)
    }
}