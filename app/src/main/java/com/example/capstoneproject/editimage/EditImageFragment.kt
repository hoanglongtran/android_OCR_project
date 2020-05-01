package com.example.capstoneproject.editimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yalantis.ucrop.UCrop

class EditImageFragment: Fragment(), EditImageContract.View
{
    override var isActive: Boolean = false
        get() = isAdded
    override lateinit var presenter: EditImageContract.Presenter

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

        fun newInstance() = EditImageFragment()

        private const val TAG = "EditImage"

    }

}