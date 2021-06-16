package com.adhitya.katalogfilm.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM filmentities WHERE filmType = 'movies'")
    fun getMovies() : DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities WHERE favorited = 1 AND filmType = 'movies'")
    fun getFavoritedMovies() : DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities WHERE filmId = :movieId AND filmType = 'movies'")
    fun getDetailsMovie(movieId: String) : LiveData<FilmEntity>

    @Query("SELECT * FROM filmentities WHERE filmType = 'tv_shows'")
    fun getTvShows() : DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities WHERE favorited = 1 AND filmType = 'tv_shows'")
    fun getFavoritedTvShows() : DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities WHERE filmId = :tvShowId")
    fun getDetailsTvShow(tvShowId: String) : LiveData<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<FilmEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<FilmEntity>)

    @Update
    fun updateMovies(movies: FilmEntity)

    @Update
    fun updateTvShows(tvShows: FilmEntity)
}