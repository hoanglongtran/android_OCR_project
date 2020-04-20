package com.example.capstoneproject.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R

class MainActivity: AppCompatActivity() {

    private lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_main)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}