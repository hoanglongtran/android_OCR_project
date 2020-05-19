package com.example.capstoneproject.editrecord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.capstoneproject.R

class EditRecordFragment : Fragment(), EditRecordContract.View {
    override lateinit var presenter: EditRecordContract.Presenter

    lateinit var patientName: EditText
    lateinit var age: EditText
    lateinit var branch: EditText
    lateinit var room: EditText
    lateinit var bed: EditText
    lateinit var development: EditText
    lateinit var medicalInstruction: EditText


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

        presenter.inferImage(context!!)

        val root: View = inflater.inflate(
            R.layout.edit_record_frag, container,
            false
        )
        with(root) {

            patientName = findViewById(R.id.patientName)
            age = findViewById(R.id.age)
            branch = findViewById(R.id.branch)
            room = findViewById(R.id.room)
            bed = findViewById(R.id.bed)
            development = findViewById(R.id.development)
            medicalInstruction = findViewById(R.id.medicalInstruction)

            //patientName.setText("Damn you", TextView.BufferType.EDITABLE)
        }

        return root
    }

    override fun setContent(firstColumn: String, secondColumn: String){
        // start some dummy thread that is different from UI thread
        Thread(Runnable {
            // try to update View of UI thread
            activity!!.runOnUiThread(java.lang.Runnable {
                medicalInstruction.setText(firstColumn, TextView.BufferType.EDITABLE)
                development.setText(secondColumn, TextView.BufferType.EDITABLE)
            })
        }).start()

    }


    companion object {

        fun newInstance() = EditRecordFragment()

        private const val TAG = "EditImage"

    }

}