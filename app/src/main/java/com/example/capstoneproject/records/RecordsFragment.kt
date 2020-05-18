package com.example.capstoneproject.records

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.editrecord.EditRecordActivity
import com.example.capstoneproject.viewrecordimage.ViewRecordImageFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yalantis.ucrop.UCrop
import java.io.File

class RecordsFragment : Fragment(), RecordsContract.View {

    override lateinit var presenter: RecordsContract.Presenter

    private lateinit var noTasksView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var tasksView: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecordImageGridAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val REQUEST_TAKE_PHOTO = 1
    override lateinit var currentImagePath: String
    lateinit var uri: Uri

    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        viewAdapter.notifyDataSetChanged()
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(
            R.layout.records_frag, container,
            false
        )
        with(root) {


            val imageList = presenter.listRecordImage(context)

            viewManager = GridLayoutManager(context,3)
            viewAdapter = RecordImageGridAdapter(context, imageList, this@RecordsFragment)

            recyclerView = findViewById<RecyclerView>(R.id.recordsLL).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter

            }

        }


        Log.d(TAG, "Setting up button")
        // Set up floating action button
        requireActivity().findViewById<FloatingActionButton>(R.id.fab_capture_record).apply {

            setOnClickListener {
                presenter.addNewRecordImage(context)
                Log.d(TAG, "finished taking picture")

            }
        }
        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            Log.d(TAG, "Result OK")
            if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                Log.d(TAG, "Edit completed")
                presenter.inferRecordImage(context!!)
            }
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Log.d(TAG, "Image captured")
                Log.d(TAG, currentImagePath)
                uri = Uri.fromFile(File((currentImagePath)))
                viewAdapter.addImage(currentImagePath)
                viewAdapter.notifyDataSetChanged()
                //presenter.createImageData(uri, context!!)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Log.d(TAG, "Edit error")
            val cropError = UCrop.getError(data!!)
        }

        if (requestCode == ViewRecordImageFragment.REQUEST_DELETE_IMAGE && resultCode == ViewRecordImageFragment.RESULT_DELETED_IMAGE ){
            Log.d(TAG, "Image Deleted")
            val deletedPosition = data?.getIntExtra(IMAGE_POSITION, -1)
            if (deletedPosition != null) {
                viewAdapter.deleteImage(deletedPosition)
                viewAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun showRecord() {
        TODO("Not yet implemented")
    }

    override fun showAddRecordImage(photoUri: Uri) {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)

            }
        }
    }

    override fun showEditRecordImage(photoUri: Uri) {

        val options = UCrop.Options().apply {
            setFreeStyleCropEnabled(true)
            setCompressionFormat(Bitmap.CompressFormat.PNG)
        }

        val ucrop: UCrop = UCrop.of(uri, uri).withOptions(options)

        ucrop.start(context!!,this)
    }

    override fun showInferRecordImage() {
        val inferImageIntent = Intent(context, EditRecordActivity::class.java).apply {

        }
        startActivity(inferImageIntent)
    }




    companion object {

        fun newInstance() = RecordsFragment()

        private const val TAG = "RecordsFragment"
        const val IMAGE_POSITION = "POSITION"

    }
}