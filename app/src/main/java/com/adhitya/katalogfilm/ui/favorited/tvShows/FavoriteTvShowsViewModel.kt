package com.adhitya.katalogfilm.ui.favorited.tvShows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity

class FavoriteTvShowsViewModel (private val filmRepository: FilmRepository) : ViewModel() {
    fun getFavoritedTvShows() : LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedTvShows()
}