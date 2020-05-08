package com.example.capstoneproject.main

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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.yalantis.ucrop.UCrop
import java.io.File
import java.io.IOException

class RecordsFragment : Fragment(), RecordsContract.View {

    override lateinit var presenter: RecordsContract.Presenter

    private lateinit var noTasksView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var tasksView: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val REQUEST_TAKE_PHOTO = 1
    override lateinit var currentImagePath: String
    lateinit var uri: Uri

    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(
            R.layout.main_frag, container,
            false
        )
        with(root) {


            val imageList = ArrayList<String>().apply {
                add("1")
                add("2")
                add("3")
                add("4")
                add("5")
                add("6")
                add("7")
                add("8")
                add("9")
                add("10")
            }

            viewManager = GridLayoutManager(context,3)
            viewAdapter = RecordImageGridAdapter(context, imageList)

            recyclerView = findViewById<RecyclerView>(R.id.recordsLL).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter

            }

            // Set up  no tasks view
/*            noTasksView = findViewById(R.id.noTasks)
            noTaskIcon = findViewById(R.id.noTasksIcon)
            noTaskMainView = findViewById(R.id.noTasksMain)*/
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
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                Log.d(TAG, "Edit completed")
                presenter.inferRecordImage(context!!)
            }
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Log.d(TAG, "editing image")
                Log.d(TAG, currentImagePath)
                uri = Uri.fromFile(File((currentImagePath)))
                presenter.editRecordImage(context!!)
                presenter.createImageData(uri, context!!)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Log.d(TAG, "Edit error")
            val cropError = UCrop.getError(data!!)
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
/*        val inferImageIntent = Intent(context, EditRecordActivity::class.java).apply {

        }
        startActivity(inferImageIntent)*/
    }

    /*private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            context!!.sendBroadcast(mediaScanIntent)
        }

    }*/



    companion object {

        fun newInstance() = RecordsFragment()

        private const val TAG = "RecordsFragment"

    }
}