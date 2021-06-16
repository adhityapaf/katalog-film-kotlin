package com.adhitya.katalogfilm.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity
import com.adhitya.katalogfilm.databinding.ActivityDetailFilmBinding
import com.adhitya.katalogfilm.databinding.ContentDetailFilmBinding
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import com.adhitya.katalogfilm.vo.Status
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

//        moviesViewModel = ViewModelProvider(
//            this,
//            factory
//        )[MoviesViewModel::class.java]
//        tvShowsViewModel = ViewModelProvider(
//            this,
//            factory
//        )[TVShowsViewModel::class.java]
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
                        detailFilmViewModel.getDetailTvShows(filmId).observe(this, Observer { detailResource ->
//                            if (detailResource != null) {
//                                when (detailResource.status) {
//                                    Status.LOADING ->  activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
//                                    Status.SUCCESS -> if (detailResource.data != null) {
//                                        activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                        activityDetailFilmBinding.content.visibility = View.VISIBLE
//                                        populateContent(detailResource.data)
//                                    }
//                                    Status.ERROR -> {
//                                        activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                                    }
//                                }
//                            }
                            detailResource?.let {
                                activityDetailFilmBinding.progressBar.visibility = View.GONE
                                activityDetailFilmBinding.content.visibility = View.VISIBLE
                                populateContent(detailResource)
                            }
                        })
                    }
                    "tv_shows" -> {
                        detailFilmViewModel.setSelectedFilm(filmId)
                        detailFilmViewModel.tvShowEntity.observe(this, Observer{ detailResource ->
//                            if (detailResource != null) {
//                                when (detailResource.status) {
//                                    Status.LOADING -> activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
//                                    Status.SUCCESS -> if (detailResource.data != null) {
//                                        activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                        activityDetailFilmBinding.content.visibility = View.VISIBLE
//                                        populateContent(detailResource.data)
//                                    }
//                                    Status.ERROR -> {
//                                        activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                        Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                                    }
//                                }
//                            }
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

    private fun populateTvShowContent(data: TvShowEntity) {
        with(detailFilmBinding) {
            textTitle.text = data.tvShowTitle
            textReleaseDate.text = data.tvShowReleaseDate
            textGenre.text = data.tvShowGenre
            textDuration.text = data.tvShowDuration
            textOverview.text = data.tvShowOverview
            Glide.with(this@DetailFilmActivity)
                .load(data.tvShowPoster)
                .into(imagePoster)
            buttonShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(this@DetailFilmActivity)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan informasi acara TV ini ke :")
                    .setText("Acara TV ${data.tvShowTitle} (${data.tvShowReleaseDate}) sepertinya bagus, yuk cek detailnya di ${data.tvShowLink}")
                    .startChooser()
            }
            buttonDetail.setOnClickListener {
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.tvShowLink))
                buttonDetail.context.startActivity(intent)
            }
        }
    }

    private fun populateMovieContent(data: MovieEntity) {
        with(detailFilmBinding) {
            textTitle.text = data.movieTitle
            textReleaseDate.text = data.movieReleaseDate
            textGenre.text = data.movieGenre
            textDuration.text = data.movieDuration
            textOverview.text = data.movieOverview
            Glide.with(this@DetailFilmActivity)
                .load(data.moviePoster)
                .into(imagePoster)
            buttonShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                    .from(this@DetailFilmActivity)
                    .setType(mimeType)
                    .setChooserTitle("Bagikan informasi film ini ke :")
                    .setText("Film ${data.movieTitle} (${data.movieReleaseDate}) sepertinya bagus, yuk cek detailnya di ${data.movieLink}")
                    .startChooser()
            }
            buttonDetail.setOnClickListener {
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.movieLink))
                buttonDetail.context.startActivity(intent)
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
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(filmEntity.link))
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
//                    if (data != null) {
//                        when (data.status) {
//                            Status.LOADING -> activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
//                            Status.SUCCESS -> if (data.data != null) {
//                                activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                val state = data.data.favorited
//                                setFavoritedState(state)
//                            }
//                            Status.ERROR -> {
//                                activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
                    data?.let {
                        val state = data.favorited
                        setFavoritedState(state)
                    }
                })
                return true
            }
            "tv_shows" -> {
                detailFilmViewModel.tvShowEntity.observe(this, { data ->
//                    if (data != null) {
//                        when (data.status) {
//                            Status.LOADING -> activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
//                            Status.SUCCESS -> if (data.data != null) {
//                                activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                val state = data.data.favorited
//                                setFavoritedState(state)
//                            }
//                            Status.ERROR -> {
//                                activityDetailFilmBinding.progressBar.visibility = View.GONE
//                                Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
//                            }
//                        }
//                    }
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