package com.adhitya.katalogfilm.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.vo.Resource

interface FilmDataSource {

    fun getMovies() : LiveData<Resource<PagedList<FilmEntity>>>

    fun getFavoritedMovies() : LiveData<PagedList<FilmEntity>>

    fun getDetailsMovies(movieId: String) : LiveData<FilmEntity>

    fun setFavoriteMovie(movie: FilmEntity, state: Boolean)

    fun getTvShows() : LiveData<Resource<PagedList<FilmEntity>>>

    fun getFavoritedTvShows() :  LiveData<PagedList<FilmEntity>>

    fun getDetailsTvShows(tvShowId: String) : LiveData<FilmEntity>

    fun setFavoriteTvShow(tvShow: FilmEntity, state: Boolean)

}