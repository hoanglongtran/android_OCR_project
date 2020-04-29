package com.example.capstoneproject.editimage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.main.RecordsFragment.Companion.newInstance
import com.example.capstoneproject.util.replaceFragmentInActivity

class EditImageActivity: AppCompatActivity()
{
    private lateinit var editImagePresenter: EditImagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_edit_image)

        val editImageFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as EditImageFragment? ?: EditImageFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        //Create the presenter
        editImagePresenter = EditImagePresenter(editImageFragment)
    }

    companion object {
        private const val TAG = "EditImageActivity"
    }
}