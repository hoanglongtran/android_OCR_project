package com.example.capstoneproject.editrecord

class EditRecordPresenter(val editRecordView: EditRecordContract.View): EditRecordContract.Presenter
{
    init {
        editRecordView.presenter = this
    }

    override fun start() {
        TODO("Do something")
    }
}