package com.example.capstoneproject.viewrecordimage

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.capstoneproject.R
import com.example.capstoneproject.editrecord.EditRecordActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yalantis.ucrop.UCrop


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
        //notify the fragment that it should participate in options menu handling

        setHasOptionsMenu(true)
        with(root) {

            recordImageView = findViewById(R.id.recordImageView)

            updateImage()

        }

        // Set up floating action button
        activity?.findViewById<FloatingActionButton>(R.id.fab_edit_image)?.setOnClickListener {
            Log.d(TAG, "Infer image")
            presenter.inferImage(context!!)
        }

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> presenter.editRecordImage(context!!)
            R.id.action_delete -> presenter.deleteImage()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.view_record_image_fragment_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                Log.d(TAG, "Edit completed")
                updateImage()
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Log.d(TAG, "Edit error")
            val cropError = UCrop.getError(data!!)
        }
    }

    override fun showEditRecordImage(photoUri: Uri) {

        val options = UCrop.Options().apply {
            setFreeStyleCropEnabled(true)
            setCompressionFormat(Bitmap.CompressFormat.PNG)
        }

        val ucrop: UCrop = UCrop.of(photoUri, photoUri).withOptions(options)

        ucrop.start(context!!,this)
    }

    override fun showRecrodImageDeleted(imagePosition: Int) {
        val intent: Intent = Intent();
        //---set the data to pass back---
        intent.putExtra("POSITION", imagePosition)
        activity?.setResult(RESULT_DELETED_IMAGE, intent)
        activity?.finish()
    }

    override fun showInferRecordImage(imagePath: String) {
        val inferImageIntent = Intent(context, EditRecordActivity::class.java).apply {
            putExtra("PATH", imagePath)
        }
        startActivity(inferImageIntent)
    }

    private fun updateImage(){
        val imgFile = presenter.getRecordImage()
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
            recordImageView.setImageBitmap(myBitmap)
        }
    }

    companion object {

        fun newInstance() = ViewRecordImageFragment()

        private const val TAG = "ViewImage"
        const val REQUEST_DELETE_IMAGE = 42
        const val RESULT_DELETED_IMAGE = 69

    }

}