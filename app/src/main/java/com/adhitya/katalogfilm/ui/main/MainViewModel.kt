package com.adhitya.katalogfilm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity
import com.adhitya.katalogfilm.vo.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(private val filmRepository: FilmRepository) : ViewModel() {
    fun getMovies() : LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getMovies()

    fun getTVShows(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getTvShows()
}