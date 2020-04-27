package com.example.capstoneproject.editrecordimage

class EditRecordImagePresenter(val editRecordImageView: EditRecordImageContract.View): EditRecordImageContract.Presenter
{
    init {
        editRecordImageView.presenter = this
    }

    override fun start() {
        TODO("Do something")
    }
}