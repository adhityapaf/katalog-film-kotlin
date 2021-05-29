package com.adhitya.katalogfilm.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository

class TVShowsViewModel (private val filmRepository: FilmRepository) : ViewModel() {

    private lateinit var filmId: String

    fun getTVShows(): LiveData<List<FilmEntity>> = filmRepository.getTvShows()

    fun setSelectedTVShows(filmId: String) {
        this.filmId = filmId
    }

    fun getDetailTvShows() : LiveData<FilmEntity> = filmRepository.getDetailsTvShows(filmId)
}