package com.hobarb.internshalanotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hobarb.internshalanotes.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //bottomNavigationView.setupWithNavController(mainNavHostFragment.findNavController())
    }
}