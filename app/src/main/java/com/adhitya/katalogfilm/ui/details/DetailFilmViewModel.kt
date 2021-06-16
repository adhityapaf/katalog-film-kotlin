package com.adhitya.katalogfilm.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity
import com.adhitya.katalogfilm.vo.Resource
import javax.inject.Inject

class DetailFilmViewModel @Inject constructor(private val filmRepository: FilmRepository) : ViewModel() {
    var filmId = MutableLiveData<String>()

    fun getDetailMovies(movieId: String) : LiveData<FilmEntity> = filmRepository.getDetailsMovies(movieId)

    fun getDetailTvShows(tvShowId: String) : LiveData<FilmEntity> = filmRepository.getDetailsTvShows(tvShowId)


    fun setSelectedFilm(filmId: String) {
        this.filmId.value = filmId
    }

    var movieEntity : LiveData<FilmEntity> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getDetailsMovies(mFilmId)
    }

    var tvShowEntity : LiveData<FilmEntity> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getDetailsTvShows(mFilmId)
    }

    fun setFavorited(filmType: String) {
        when (filmType) {
            "movies" -> {
                val movie = movieEntity.value
                val newState = !movie?.favorited!!
                filmRepository.setFavoriteMovie(movie, newState)
            }
            "tv_shows" -> {
                val tvShow = tvShowEntity.value
                val newState = !tvShow?.favorited!!
                filmRepository.setFavoriteTvShow(tvShow, newState)
            }
        }
    }
}