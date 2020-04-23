package com.example.capstoneproject.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.capstoneproject.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecordsFragment : Fragment(), RecordsContract.View {

    override lateinit var presenter: RecordsContract.Presenter

    private lateinit var noTasksView: View
    private lateinit var noTaskIcon: ImageView
    private lateinit var noTaskMainView: TextView
    private lateinit var noTaskAddView: TextView
    private lateinit var tasksView: LinearLayout
    private lateinit var filteringLabelView: TextView


    val REQUEST_IMAGE_CAPTURE = 1
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

        setOnClickListener { presenter.addNewRecord() }
        }
        return root
    }


    /*override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.main_frag, container, false)

        // Set up tasks view
        with(root) {
            *//*val listView = findViewById<ListView>(R.id.tasks_list)
            Log.d(TAG, "Setting up container")
            // Set up progress indicator
            findViewById<RelativeLayout>(R.id.tasksContainer)


            tasksView = findViewById(R.id.tasksLL)*//*

            // Set up  no tasks view
            noTasksView = findViewById(R.id.noTasks)
            noTaskIcon = findViewById(R.id.noTasksIcon)
            noTaskMainView = findViewById(R.id.noTasksMain)

        }


        Log.d(TAG, "Setting up button")
        // Set up floating action button
        *//*requireActivity().findViewById<FloatingActionButton>(R.id.fab_capture_record).apply {

            setOnClickListener { presenter.addNewRecord() }
        }*//*


        return root
    }*/

    override fun showRecord() {
        TODO("Not yet implemented")
    }

    override fun showAddRecord() {


        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    companion object {

        fun newInstance() = RecordsFragment()

        private const val TAG = "RecordsFragment"

    }
}