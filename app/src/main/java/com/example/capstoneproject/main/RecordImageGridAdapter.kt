package com.example.capstoneproject.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.record_image_view.view.*

class RecordImageGridAdapter(private val c: Context, private val images: ArrayList<String>) :
    RecyclerView.Adapter<RecordImageGridAdapter.ImageViewHolder>() {


    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(c).inflate(R.layout.record_image_view, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val path = images[position]

        Picasso.get()
            .load("content://com.example.android.fileprovider/my_images/RECORD_20200511_110422_6254468004404327641.png")
            .resize(600, 600)
            .centerCrop()
            .into(holder.iv)
        //holder.iv.setImageResource(R.drawable.y430i)
        holder.iv.setOnClickListener {
            //handle click event on image
        }
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv: ImageView = view.findViewById(R.id.iv)
    }
}