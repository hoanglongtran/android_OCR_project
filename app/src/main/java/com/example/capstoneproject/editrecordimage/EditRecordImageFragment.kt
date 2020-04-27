package com.example.capstoneproject.editrecordimage

import androidx.fragment.app.Fragment

class EditRecordImageFragment: Fragment(), EditRecordImageContract.View
{
    override var isActive: Boolean = false
        get() = isAdded
    override lateinit var presenter: EditRecordImageContract.Presenter


}