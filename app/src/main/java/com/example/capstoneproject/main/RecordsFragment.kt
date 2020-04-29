package com.example.capstoneproject.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.capstoneproject.R
import com.example.capstoneproject.editimage.EditImageActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.IOException

class RecordsFragment : Fragment(), RecordsContract.View {

    override lateinit var presenter: RecordsContract.Presenter

    private lateinit var noTasksView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var tasksView: LinearLayout


    private val REQUEST_TAKE_PHOTO = 1
    override lateinit var currentPhotoPath: String

    override var isActive: Boolean = false
        get() = isAdded

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.main_frag, container,
            false)
        with(root) {
            // Set up  no tasks view
            noTasksView = findViewById(R.id.noTasks)
            noTaskIcon = findViewById(R.id.noTasksIcon)
            noTaskMainView = findViewById(R.id.noTasksMain)
        }

        Log.d(TAG, "Setting up button")
        // Set up floating action button
        requireActivity().findViewById<FloatingActionButton>(R.id.fab_capture_record).apply {

        setOnClickListener { presenter.addNewRecord(context) }
        }
        return root
    }


    override fun showRecord() {
        TODO("Not yet implemented")
    }

    override fun showAddRecord(photoFile: File?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                // Create the File where the photo should go

                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        activity!!,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
        galleryAddPic()
    }

    override fun showEditRecordImage(){
        val intent: Intent = Intent(context, EditImageActivity::class.java).apply {
            putExtra("image_path", currentPhotoPath)
        }
        startActivity(intent)
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            context!!.sendBroadcast(mediaScanIntent)
        }

    }





    companion object {

        fun newInstance() = RecordsFragment()

        private const val TAG = "RecordsFragment"

    }
}