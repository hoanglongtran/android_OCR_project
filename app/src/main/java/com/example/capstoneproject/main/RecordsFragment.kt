package com.example.capstoneproject.main

import android.app.Activity.RESULT_OK
import android.content.Context
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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.capstoneproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.main_frag.*
import kotlinx.android.synthetic.main.record_image_view.view.*
import java.io.File

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

            //val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //recordsLL.layoutManager = sglm

            val imageList = ArrayList<String>()
            imageList.add("https://farm5.staticflickr.com/4403/36538794702_83fd8b63b7_c.jpg")
            imageList.add("https://farm5.staticflickr.com/4354/35684440714_434610d1d6_c.jpg")
            imageList.add("https://farm5.staticflickr.com/4301/35690634410_f5d0e312cb_c.jpg")
            imageList.add("https://farm4.staticflickr.com/3854/32890526884_7dc068fedd_c.jpg")
            imageList.add("https://farm8.staticflickr.com/7787/18143831616_a239c78056_c.jpg")
            imageList.add("https://farm9.staticflickr.com/8745/16657401480_57653ac8b0_c.jpg")
            imageList.add("https://farm3.staticflickr.com/2917/14144166232_44613c53c7_c.jpg")
            imageList.add("https://farm8.staticflickr.com/7453/13960410788_3dd02b7a02_c.jpg")
            imageList.add("https://farm1.staticflickr.com/920/29297133218_de38a7e4c8_c.jpg")
            imageList.add("https://farm2.staticflickr.com/1788/42989123072_6720c9608d_c.jpg")
            imageList.add("https://farm1.staticflickr.com/888/29062858008_89851766c9_c.jpg")
            imageList.add("https://farm2.staticflickr.com/1731/27940806257_8067196b41_c.jpg")
            imageList.add("https://farm1.staticflickr.com/884/42745897912_ff65398e38_c.jpg")
            imageList.add("https://farm2.staticflickr.com/1829/27971893037_1858467f9a_c.jpg")
            imageList.add("https://farm2.staticflickr.com/1822/41996470025_414452d7a0_c.jpg")
            imageList.add("https://farm2.staticflickr.com/1793/42937679651_3094ebb2b9_c.jpg")
            imageList.add("https://farm1.staticflickr.com/892/42078661914_b940d96992_c.jpg")
            //val igka = ImageGridAdapter(context, imageList)
            //recordsLL.adapter = igka
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
                presenter.addNewRecord(context)
                Log.d(TAG, "finished taking picture")

            }
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
            }
            if (requestCode == REQUEST_TAKE_PHOTO) {
                Log.d(TAG, "editing image")
                presenter.editRecordImage(context!!)
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
        }
    }

    override fun showRecord() {
        TODO("Not yet implemented")
    }

    override fun showAddRecord(photoUri: Uri) {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)

            }
        }


    }

    override fun showEditRecordImage(photoUri: Uri) {
        var uri: Uri = Uri.fromFile(File((currentImagePath)))

        val options = UCrop.Options().apply {
            setFreeStyleCropEnabled(true)
            setCompressionFormat(Bitmap.CompressFormat.PNG)
        }

        val ucrop: UCrop = UCrop.of(uri, uri).withOptions(options)

        activity?.let { ucrop.start(it) }
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