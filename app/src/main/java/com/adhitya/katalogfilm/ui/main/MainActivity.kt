package com.adhitya.katalogfilm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val sectionPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityMainBinding.viewPager.adapter = sectionPagerAdapter
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager)

        supportActionBar?.elevation = 0f
    }
}