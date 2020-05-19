package com.example.capstoneproject.editrecord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.util.replaceFragmentInActivity
import com.example.capstoneproject.util.setupActionBar

class EditRecordActivity: AppCompatActivity()
{
    private lateinit var editRecordPresenter: EditRecordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.edit_record_act)

        val imagePath = intent.getStringExtra(IMAGE_PATH)

        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val editImageFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as EditRecordFragment? ?: EditRecordFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        //Create the presenter
        editRecordPresenter = EditRecordPresenter(imagePath, editImageFragment)
    }

    companion object {
        private const val TAG = "EditImageActivity"
        private const val IMAGE_PATH = "PATH"
    }
}