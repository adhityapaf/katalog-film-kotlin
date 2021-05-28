package com.adhitya.katalogfilm.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.databinding.ActivityDetailFilmBinding
import com.adhitya.katalogfilm.databinding.ContentDetailFilmBinding
import com.adhitya.katalogfilm.ui.movies.MoviesViewModel
import com.adhitya.katalogfilm.ui.tv_shows.TVShowsViewModel
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailFilmActivity : AppCompatActivity() {

    companion object {
        const val FILM_TYPE = "film_type"
        const val FILM_EXTRA = "film_extra"
    }

    private lateinit var detailFilmBinding: ContentDetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailFilmBinding = activityDetailFilmBinding.detailContent
        setContentView(activityDetailFilmBinding.root)
        setSupportActionBar(activityDetailFilmBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val moviesViewModel = ViewModelProvider(
            this,
            factory
        )[MoviesViewModel::class.java]
        val tvShowsViewModel = ViewModelProvider(
            this,
            factory
        )[TVShowsViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(FILM_EXTRA)
            val filmType = extras.getString(FILM_TYPE)
            if (filmId != null) {
                activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                activityDetailFilmBinding.content.visibility = View.INVISIBLE
                when (filmType) {
                    "movies" -> {
                        moviesViewModel.setSelectedMovies(filmId)
                        moviesViewModel.getDetailMovies().observe(this, { movies ->
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            activityDetailFilmBinding.content.visibility = View.VISIBLE
                            populateContent(movies)
                        })
                    }
                    "tv_shows" -> {
                        tvShowsViewModel.setSelectedTVShows(filmId)
                        tvShowsViewModel.getDetailTvShows().observe(this, {tvShows ->
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            activityDetailFilmBinding.content.visibility = View.VISIBLE
                            populateContent(tvShows)
                        })
                    }
                    else -> Toast.makeText(this, R.string.no_contents, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun populateContent(filmEntity: FilmEntity) {
        with(detailFilmBinding) {
            textTitle.text = filmEntity.title
            textReleaseDate.text = filmEntity.releaseDate
            textGenre.text = filmEntity.genre
            textDuration.text = filmEntity.duration
            textOverview.text = filmEntity.overview
            imagePoster.setImageResource(filmEntity.poster.toInt())
            buttonShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(this@DetailFilmActivity)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan informasi film ini ke :")
                    .setText("Film ${filmEntity.title} (${filmEntity.releaseDate}) sepertinya bagus, yuk cek detailnya di ${filmEntity.link}")
                    .startChooser()
            }
            buttonDetail.setOnClickListener {
                val intent: Intent = Uri.parse(filmEntity.link).let { webpage ->
                    Intent(Intent.ACTION_VIEW, webpage)
                }
                buttonDetail.context.startActivity(intent)
            }
        }
    }
}