package com.udacoding.cloudstoragefirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.item_layout.view.*

class DataAdapter(private val data: List<StorageReference>?) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: StorageReference?) {

            Glide.with(view.context)
                .load("https://firebasestorage.googleapis.com/v0/b/cloudstoragefirebase-53108.appspot.com/o/images%2F${item?.name}?alt=media")
                .into(view.imageView)

            view.textView.text = item?.name

        }

    }
}