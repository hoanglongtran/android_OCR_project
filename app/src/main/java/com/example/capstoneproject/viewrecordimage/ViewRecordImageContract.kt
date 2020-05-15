package com.example.capstoneproject.viewrecordimage

import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView

interface ViewRecordImageContract {
    interface View: BaseView<Presenter> {

        var isActive: Boolean

    }

    interface Presenter : BasePresenter {
        fun editImage()

        fun deleteImage()

        fun inferImage()
    }
}