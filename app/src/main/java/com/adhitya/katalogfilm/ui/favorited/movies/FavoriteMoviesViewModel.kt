package com.adhitya.katalogfilm.ui.favorited.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity

class FavoriteMoviesViewModel (private val filmRepository: FilmRepository) : ViewModel() {
    fun getFavoritedMovies() : LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedMovies()
}