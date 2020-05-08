package com.example.capstoneproject.editrecord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.util.replaceFragmentInActivity

class EditRecordActivity: AppCompatActivity()
{
    private lateinit var editRecordPresenter: EditRecordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_record)

        val editImageFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as EditRecordFragment? ?: EditRecordFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        //Create the presenter
        editRecordPresenter = EditRecordPresenter(editImageFragment)
    }

    companion object {
        private const val TAG = "EditImageActivity"
    }
}