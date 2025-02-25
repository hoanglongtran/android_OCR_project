package com.example.capstoneproject.viewrecordimage

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.util.replaceFragmentInActivity
import com.example.capstoneproject.util.setupActionBar

class ViewRecordImageActivity: AppCompatActivity()
{
    private lateinit var viewRecordImagePresenter: ViewRecordImagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_record_image_act)
        val imagePath = intent.getStringExtra(IMAGE_PATH)
        val imagePosition = intent.getIntExtra(IMAGE_POSITION, 0)

        // Set up the toolbar.
        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val viewRecordImageFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ViewRecordImageFragment? ?: ViewRecordImageFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        //Create the presenter
        viewRecordImagePresenter = ViewRecordImagePresenter(imagePath, imagePosition, viewRecordImageFragment)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val TAG = "ViewRecordImageActivity"
        const val IMAGE_PATH = "PATH"
        const val IMAGE_POSITION = "POSITION"
    }

}


