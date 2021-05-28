package com.adhitya.katalogfilm.data.source

import androidx.lifecycle.LiveData
import com.adhitya.katalogfilm.data.FilmEntity

interface FilmDataSource {

    fun getMovies() : LiveData<List<FilmEntity>>

    fun getDetailsMovies(filmId: String) : LiveData<FilmEntity>

    fun getTvShows(): LiveData<List<FilmEntity>>

    fun getDetailsTvShows(filmId: String) : LiveData<FilmEntity>

}