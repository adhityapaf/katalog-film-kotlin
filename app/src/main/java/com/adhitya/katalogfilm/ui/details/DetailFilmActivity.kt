package com.adhitya.katalogfilm.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

        val moviesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MoviesViewModel::class.java]
        val tvShowsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[TVShowsViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val filmId = extras.getString(FILM_EXTRA)
            val filmType = extras.getString(FILM_TYPE)
            if (filmId != null) {
                when (filmType) {
                    "movies" -> {
                        moviesViewModel.setSelectedMovies(filmId)
                        populateContent(moviesViewModel.getDetailMovies())
                    }
                    "tv_shows" -> {
                        tvShowsViewModel.setSelectedTVShows(filmId)
                        populateContent(tvShowsViewModel.getDetailTVShows())
                    }
                    else -> Toast.makeText(this, "Konten Tidak Ada", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun populateContent(filmEntity: FilmEntity) {
        with(detailFilmBinding) {
            textTitle.text = filmEntity.title
            textReleaseDate.text = filmEntity.release_date
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
                    .setText("Film ${filmEntity.title} (${filmEntity.release_date}) sepertinya bagus, yuk cek detailnya di ${filmEntity.link}")
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