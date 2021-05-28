package com.adhitya.katalogfilm.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.utils.FilmsData

class MoviesViewModel (private val filmRepository: FilmRepository) : ViewModel() {
    private lateinit var filmId: String
    fun getMovies(): LiveData<List<FilmEntity>> = filmRepository.getMovies()

    fun setSelectedMovies(filmId: String) {
        this.filmId = filmId
    }

    fun getDetailMovies() : LiveData<FilmEntity> = filmRepository.getDetailsMovies(filmId)


//
//    fun getDetailMovies(): FilmEntity {
//        lateinit var movies: FilmEntity
//        val moviesEntities = FilmsData.generateMoviesData()
//        for (moviesEntity in moviesEntities) {
//            if (moviesEntity.filmId == filmId) {
//                movies = moviesEntity
//            }
//        }
//        return movies
//    }
}