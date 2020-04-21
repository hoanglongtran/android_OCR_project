package com.example.capstoneproject.main

import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainActivityContract {

    interface View: BaseView<Presenter> {

        var isActive: Boolean

    }

    interface Presenter : BasePresenter {

        fun addNewRecord()

        fun openRecord()

        fun completeTask()

        fun activateTask()
    }
}