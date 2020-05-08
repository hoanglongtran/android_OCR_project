package com.example.capstoneproject.editrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capstoneproject.R
import kotlinx.android.synthetic.main.edit_record_frag.view.*

class EditRecordFragment: Fragment(), EditRecordContract.View
{
    override var isActive: Boolean = false
        get() = isAdded
    override lateinit var presenter: EditRecordContract.Presenter

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
            R.layout.edit_record_frag, container,
            false
        )
        with(root) {


            var viewManager: TextView = findViewById(R.id.textView)


        }

        return root
    }


    companion object {

        fun newInstance() = EditRecordFragment()

        private const val TAG = "EditImage"

    }

}