package com.adhitya.katalogfilm.ui.favorited

import android.os.Bundle
import android.view.MenuItem
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.databinding.ActivityFavoriteMainBinding
import dagger.android.support.DaggerAppCompatActivity

class FavoriteMainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityFavoriteMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = FavoriteSectionsPagerAdapter(this, supportFragmentManager)
        binding.favoriteViewPager.adapter = sectionsPagerAdapter
        binding.favoriteTabs.setupWithViewPager(binding.favoriteViewPager)

        supportActionBar?.elevation = 0f
        supportActionBar?.setTitle(R.string.favorite_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            true
        }
    }
}