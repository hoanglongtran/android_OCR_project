package com.example.capstoneproject.main

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.squareup.picasso.Picasso
import com.yalantis.ucrop.UCrop
import java.io.File

class RecordImageGridAdapter(private val context: Context, private val images: ArrayList<String>) :
    RecyclerView.Adapter<RecordImageGridAdapter.ImageViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.record_image_view, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val path = images[position]
        val uri = Uri.fromFile(File((path)))
        Picasso.get()
            .load(uri)
            .resize(600, 600)
            .centerCrop()
            .into(holder.iv)

        holder.iv.setOnClickListener {
            //handle click event on image
            val options = UCrop.Options().apply {
                setFreeStyleCropEnabled(true)
                setCompressionFormat(Bitmap.CompressFormat.PNG)
            }

            val ucrop: UCrop = UCrop.of(uri, uri).withOptions(options)

            ucrop.start(context as Activity)
        }
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv)
    }
}