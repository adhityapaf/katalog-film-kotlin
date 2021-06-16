package com.adhitya.katalogfilm.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.MovieEntity
import com.adhitya.katalogfilm.vo.Resource

class MoviesViewModel (private val filmRepository: FilmRepository) : ViewModel() {
    private lateinit var movieId: String
//    fun getMovies() : LiveData<Resource<PagedList<MovieEntity>>> = filmRepository.getMovies()

    fun setSelectedMovies(movieId: String) {
        this.movieId = movieId
    }

    fun getDetailMovies() : LiveData<FilmEntity> = filmRepository.getDetailsMovies(movieId)
}