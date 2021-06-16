package com.adhitya.katalogfilm.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity
import com.adhitya.katalogfilm.data.source.local.room.FilmDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mFilmDao: FilmDao) {
    companion object {
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(filmDao: FilmDao) : LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getMovies() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getMovies()

    fun getFavoritedMovies() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedMovies()

    fun getDetailsMovie(movieId: String) :  LiveData<FilmEntity> = mFilmDao.getDetailsMovie(movieId)

    fun getTvShows() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getTvShows()

    fun getFavoritedTvShows() : DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedTvShows()

    fun getDetailsTvShow(tvShowId: String) : LiveData<FilmEntity> = mFilmDao.getDetailsTvShow(tvShowId)

    fun insertMovies(movies: List<FilmEntity>) = mFilmDao.insertMovies(movies)

    fun insertTvShows(tvShows: List<FilmEntity>) = mFilmDao.insertTvShows(tvShows)

    fun setMovieFavorite(movies: FilmEntity, newState: Boolean) {
        movies.favorited = newState
        mFilmDao.updateMovies(movies)
    }

    fun setTvShowFavorite(tvShows: FilmEntity, newState: Boolean) {
        tvShows.favorited = newState
        mFilmDao.updateTvShows(tvShows)
    }
}