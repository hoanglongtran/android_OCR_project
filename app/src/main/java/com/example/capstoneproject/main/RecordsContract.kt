package com.example.capstoneproject.main

import com.example.capstoneproject.BasePresenter
import com.example.capstoneproject.BaseView

/**
 * This specifies the contract between the view and the presenter.
 */
interface RecordsContract {

    interface View: BaseView<Presenter> {

        var isActive: Boolean

        fun showRecord()

        fun showAddRecord()



    }

    interface Presenter : BasePresenter {
        fun loadRecords()

        fun addNewRecord()

        fun openRecord()

        fun deleteRecord()
    }
}