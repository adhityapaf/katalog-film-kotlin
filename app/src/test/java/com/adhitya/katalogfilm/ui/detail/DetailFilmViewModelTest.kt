package com.adhitya.katalogfilm.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.ui.details.DetailFilmViewModel
import com.adhitya.katalogfilm.utils.FilmsData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {
    private val moviesData = FilmsData.generateMoviesData()[0]
    private val moviesId = moviesData.filmId
    private val tvShowsData = FilmsData.generateTVShowsData()[0]
    private val tvShowsId = tvShowsData.filmId

    private lateinit var viewModel : DetailFilmViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var moviesObserver: Observer<FilmEntity>

    @Mock
    private lateinit var tvShowsObserver : Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)
    }

    @Test
    fun getDetailsMovie() {
        val movies = MutableLiveData<FilmEntity>()
        movies.value = moviesData

        Mockito.`when`(filmRepository.getDetailsMovies(moviesId)).thenReturn(movies)
        val moviesDetailsData = viewModel.getDetailMovies(moviesId).value
        assertNotNull(moviesDetailsData)
        if (moviesDetailsData != null) {
            assertEquals(moviesData.filmId, moviesDetailsData.filmId)
            assertEquals(moviesData.title, moviesDetailsData.title)
            assertEquals(moviesData.releaseDate, moviesDetailsData.releaseDate)
            assertEquals(moviesData.genre, moviesDetailsData.genre)
            assertEquals(moviesData.duration, moviesDetailsData.duration)
            assertEquals(moviesData.poster, moviesDetailsData.poster)
            assertEquals(moviesData.overview, moviesDetailsData.overview)
            viewModel.getDetailMovies(moviesId).observeForever(moviesObserver)
            Mockito.verify(moviesObserver).onChanged(moviesData)
        }
    }

    @Test
    fun getDetailsTvShow() {
        val tvShows = MutableLiveData<FilmEntity>()
        tvShows.value = tvShowsData
        Mockito.`when`(filmRepository.getDetailsTvShows(tvShowsId)).thenReturn(tvShows)
        val tvShowsDetailsData = viewModel.getDetailTvShows(tvShowsId).value
        assertNotNull(tvShowsDetailsData)
        if (tvShowsDetailsData != null) {
            assertEquals(tvShowsData.filmId, tvShowsDetailsData.filmId)
            assertEquals(tvShowsData.poster, tvShowsDetailsData.poster)
            assertEquals(tvShowsData.title, tvShowsDetailsData.title)
            assertEquals(tvShowsData.releaseDate, tvShowsDetailsData.releaseDate)
            assertEquals(tvShowsData.genre, tvShowsDetailsData.genre)
            assertEquals(tvShowsData.duration, tvShowsDetailsData.duration)
            assertEquals(tvShowsData.overview, tvShowsDetailsData.overview)
            viewModel.getDetailTvShows(tvShowsId).observeForever(tvShowsObserver)
            Mockito.verify(tvShowsObserver).onChanged(tvShowsData)
        }
    }
}