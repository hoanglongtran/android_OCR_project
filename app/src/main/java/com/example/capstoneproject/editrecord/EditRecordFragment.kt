package com.example.capstoneproject.editrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class EditRecordFragment: Fragment(), EditRecordContract.View
{
    override var isActive: Boolean = false
        get() = isAdded
    override lateinit var presenter: EditRecordContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)


    }


    companion object {

        fun newInstance() = EditRecordFragment()

        private const val TAG = "EditImage"

    }

}