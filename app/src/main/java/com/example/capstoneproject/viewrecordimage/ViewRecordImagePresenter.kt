package com.example.capstoneproject.viewrecordimage

class ViewRecordImagePresenter(val editRecordView: ViewRecordImageContract.View): ViewRecordImageContract.Presenter
{
    init {
        editRecordView.presenter = this
    }

    override fun editImage() {
        TODO("Not yet implemented")
    }

    override fun deleteImage() {
        TODO("Not yet implemented")
    }

    override fun inferImage() {
        TODO("Not yet implemented")
    }

    override fun start() {
        TODO("Do something")
    }
}