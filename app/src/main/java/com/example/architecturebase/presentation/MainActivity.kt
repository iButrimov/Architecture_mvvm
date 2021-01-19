package com.example.architecturebase.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.architecturebase.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ViewFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                add(R.id.fragmentContainerView, fragment)
                commit()
            }
        }
    }
}