package com.serhat.repoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.serhat.repoapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.frMainNavFragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.frMainNavFragment).navigateUp()
}
