package com.example.capstoneproject.editrecord

import android.content.Context
import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView
import org.json.JSONObject

interface EditRecordContract {
    interface View: BaseView<Presenter> {

        var isActive: Boolean

        fun setContent(firstColumn: String, secondColumn: String)



    }

    interface Presenter : BasePresenter {

        fun inferImage(context: Context)

        fun uploadImage(context: Context)

        fun createImageData(context: Context): ByteArray?
    }
}