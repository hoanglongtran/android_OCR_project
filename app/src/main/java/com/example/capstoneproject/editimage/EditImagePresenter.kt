package com.example.capstoneproject.editimage

class EditImagePresenter(val editImageView: EditImageContract.View): EditImageContract.Presenter
{
    init {
        editImageView.presenter = this
    }

    override fun start() {
        TODO("Do something")
    }
}