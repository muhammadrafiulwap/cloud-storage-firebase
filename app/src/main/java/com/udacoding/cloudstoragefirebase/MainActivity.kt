package com.udacoding.cloudstoragefirebase

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private var mStorageRef: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mStorageRef = FirebaseStorage.getInstance().reference

//        upload()

//        getData()

        showData()

    }

    private fun showData() {
        val storageImage = mStorageRef?.child("images/")
        storageImage?.listAll()
            ?.addOnSuccessListener {
                recyclerView.adapter = DataAdapter(it.items)
                progressBar.isIndeterminate = false
                progressBar.visibility = View.GONE
            }
            ?.addOnFailureListener {
                Log.d("TAG", "onCreate: ${it.message}")
            }


    }

//    private fun getData() {
//        val storageImage = mStorageRef?.child("images/")
//        storageImage?.listAll()
//            ?.addOnSuccessListener {
//                Log.d("TAG", "onCreate: path: ${it?.items?.get(0)?.path}")
//                Log.d("TAG", "onCreate: url: ${it?.items?.get(0)?.downloadUrl}")
//                Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/cloudstoragefirebase-53108.appspot.com/o/images%2F${it?.items?.get(0)?.name}?alt=media").into(imageView)
//            }
//            ?.addOnFailureListener {
//                Log.d("TAG", "onCreate: ${it.message}")
//            }
//    }

    private fun upload() {
        val file = Uri.fromFile(File("/storage/emulated/0/Download/acf5af9ab9fc6d888a0569fa7f257067.jpg"))
        val storageRef = mStorageRef?.child("images/acf5af9ab9fc6d888a0569fa7f257067.jpg")

        storageRef?.putFile(file)
            ?.addOnSuccessListener {
                Log.d("TAG", "onCreate: success upload")
            }
            ?.addOnFailureListener {
                Log.d("TAG", "onCreate: failed upload : ${it.message}")
            }
    }
}