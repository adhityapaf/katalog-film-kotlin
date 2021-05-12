package com.adhitya.katalogfilm.ui.details

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.adhitya.katalogfilm.R

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val FILM_TYPE = "film_type"
        const val FILM_EXTRA = "film_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_film)
        setSupportActionBar(findViewById(R.id.toolbar))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}