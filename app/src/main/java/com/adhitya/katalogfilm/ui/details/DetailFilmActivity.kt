package com.adhitya.katalogfilm.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.databinding.ActivityDetailFilmBinding
import com.adhitya.katalogfilm.databinding.ContentDetailFilmBinding
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import com.adhitya.katalogfilm.vo.Resource
import com.bumptech.glide.Glide
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailFilmActivity : DaggerAppCompatActivity() {

    private lateinit var detailFilmViewModel: DetailFilmViewModel

    companion object {
        const val FILM_TYPE = "film_type"
        const val FILM_EXTRA = "film_extra"
    }

    private lateinit var detailFilmBinding: ContentDetailFilmBinding
    private lateinit var activityDetailFilmBinding: ActivityDetailFilmBinding
    private var menu: Menu? = null

    private lateinit var filmId: String
    private lateinit var filmType: String

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailFilmBinding = activityDetailFilmBinding.detailContent
        setContentView(activityDetailFilmBinding.root)
        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailFilmViewModel = ViewModelProvider(
            this,
            factory
        )[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
             filmId = extras.getString(FILM_EXTRA).toString()
             filmType = extras.getString(FILM_TYPE).toString()
            if (filmId != null) {
                activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                activityDetailFilmBinding.content.visibility = View.INVISIBLE
                when (filmType) {
                    "movies" -> {
                        detailFilmViewModel.setSelectedFilm(filmId)
                        detailFilmViewModel.getDetailMovies(filmId).observe(this, { detailResource ->
                            detailResource?.let {
                                activityDetailFilmBinding.progressBar.visibility = View.GONE
                                activityDetailFilmBinding.content.visibility = View.VISIBLE
                                populateContent(detailResource)
                            }
                        })
                    }
                    "tv_shows" -> {
                        detailFilmViewModel.setSelectedFilm(filmId)
                        detailFilmViewModel.getDetailTvShows(filmId).observe(this, { detailResource ->
                            detailResource?.let {
                                activityDetailFilmBinding.progressBar.visibility = View.GONE
                                activityDetailFilmBinding.content.visibility = View.VISIBLE
                                populateContent(detailResource)
                            }

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
            Glide.with(this@DetailFilmActivity)
                .load(filmEntity.poster)
                .into(imagePoster)
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
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(filmEntity.link))
                buttonDetail.context.startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        this.menu = menu
        when (filmType) {
            "movies" -> {
                detailFilmViewModel.movieEntity.observe(this, { data ->
                    data?.let {
                        val state = data.favorited
                        setFavoritedState(state)
                    }
                })
                return true
            }
            "tv_shows" -> {
                detailFilmViewModel.tvShowEntity.observe(this, { data ->
                    data?.let {
                        val state = data.favorited
                        setFavoritedState(state)
                    }
                })
                return true
            }
            else -> return true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            detailFilmViewModel.setFavorited(filmType)
            return true
        } else if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoritedState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_unfavorited_white)
        }
    }
}