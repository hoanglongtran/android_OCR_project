package com.example.capstoneproject.records

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.viewrecordimage.ViewRecordImageActivity
import com.example.capstoneproject.viewrecordimage.ViewRecordImageFragment
import com.squareup.picasso.Picasso
import java.io.File


class RecordImageGridAdapter(private val context: Context, private val images: ArrayList<String>, val fragment: Fragment) :
    RecyclerView.Adapter<RecordImageGridAdapter.ImageViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_record_image_view, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val path = images[position]
        val uri = Uri.fromFile(File((path)))
        Log.d("Uri", uri.toString())
        Picasso.get()
            .load(uri)
            .resize(200, 200)
            .centerCrop()
            .into(holder.iv)

        holder.iv.setOnClickListener {
            //handle click event on image
            val intent = Intent(context, ViewRecordImageActivity::class.java)
            val bundle = Bundle()

            intent.putExtra("PATH", path)
            intent.putExtra("POSITION", position)
            //context.startActivity(intent)

            fragment.startActivityForResult(intent, ViewRecordImageFragment.REQUEST_DELETE_IMAGE)


/*            val options = UCrop.Options().apply {
                setFreeStyleCropEnabled(true)
                setCompressionFormat(Bitmap.CompressFormat.PNG)
            }

            val ucrop: UCrop = UCrop.of(uri, uri).withOptions(options)

            ucrop.start(context as Activity)*/
        }
    }
    fun addImage(imagePath: String){
        images.add(imagePath)
    }

    fun deleteImage(imagePosition: Int){
        images.removeAt(imagePosition)
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv)
    }
}