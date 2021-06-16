package com.adhitya.katalogfilm.ui.favorited

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import javax.inject.Inject

class FavoriteMainViewModel @Inject constructor(private val filmRepository: FilmRepository) : ViewModel() {
    fun getFavoritedMovies() : LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedMovies()

    fun getFavoritedTvShows() : LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedTvShows()

}