package com.aman.roommvvmcoroutinenavigation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aman.roommvvmcoroutinenavigation.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}