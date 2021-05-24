package com.adhitya.katalogfilm.ui.movies

import com.adhitya.katalogfilm.utils.FilmsData
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel
    private val movieData = FilmsData.generateMoviesData()[0]
    private val movieId = movieData.filmId

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
        viewModel.setSelectedMovies(movieId)
    }

    @Test
    fun getMovies() {
        val movieEntity = viewModel.getMovies()
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity.size)
    }

    @Test
    fun getDetailMovies() {
        val movieEntity = viewModel.getDetailMovies()
        assertNotNull(movieEntity)
        assertEquals(movieData.filmId, movieEntity.filmId)
        assertEquals(movieData.release_date, movieEntity.release_date)
        assertEquals(movieData.link, movieEntity.link)
        assertEquals(movieData.title, movieEntity.title)
        assertEquals(movieData.duration, movieEntity.duration)
        assertEquals(movieData.genre, movieEntity.genre)
        assertEquals(movieData.overview, movieEntity.overview)
        assertEquals(movieData.poster, movieEntity.poster)
    }
}