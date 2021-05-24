package com.adhitya.katalogfilm.ui.movies

import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.utils.FilmsData

class MoviesViewModel : ViewModel() {
    private lateinit var filmId: String
    fun getMovies(): ArrayList<FilmEntity> = FilmsData.generateMoviesData()

    fun setSelectedMovies(filmId: String) {
        this.filmId = filmId
    }

    fun getDetailMovies(): FilmEntity {
        lateinit var movies: FilmEntity
        val moviesEntities = FilmsData.generateMoviesData()
        for (moviesEntity in moviesEntities) {
            if (moviesEntity.filmId == filmId) {
                movies = moviesEntity
            }
        }
        return movies
    }
}