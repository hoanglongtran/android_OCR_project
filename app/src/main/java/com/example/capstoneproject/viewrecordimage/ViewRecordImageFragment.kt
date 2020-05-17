package com.example.capstoneproject.viewrecordimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capstoneproject.R

class ViewRecordImageFragment : Fragment(), ViewRecordImageContract.View {
    override lateinit var presenter: ViewRecordImageContract.Presenter

    private lateinit var recordImageView: ImageView

    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        //presenter.start()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root: View = inflater.inflate(
            R.layout.view_record_image_frag, container,
            false
        )
        with(root) {
            recordImageView = findViewById(R.id.recordImageView)

        }

        return root
    }


    companion object {

        fun newInstance() = ViewRecordImageFragment()

        private const val TAG = "ViewImage"

    }

}