package com.adhitya.katalogfilm.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.utils.FilmsData
import com.nhaarman.mockitokotlin2.verify
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
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel
    private val dummyMovies = FilmsData.generateMoviesData()[0]
    private val moviesId = dummyMovies.filmId


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @Mock
    private lateinit var filmRepository : FilmRepository
    
    @Mock
    private lateinit var observer: Observer<List<FilmEntity>>
    
    @Mock
    private lateinit var detailObserver : Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(filmRepository)
        viewModel.setSelectedMovies(moviesId)
    }

    @Test
    fun getMovies() {
        val movieListDummy = FilmsData.generateMoviesData()
        val movies = MutableLiveData<List<FilmEntity>>()
        movies.value = movieListDummy
        Mockito.`when`(filmRepository.getMovies()).thenReturn(movies)
        val movieEntity = viewModel.getMovies().value
        verify(filmRepository).getMovies()
        assertNotNull(movieEntity)
        assertEquals(19, movieEntity?.size)
        
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieListDummy)
    }

    @Test
    fun getDetailMovies() {
        val movies = MutableLiveData<FilmEntity>()
        movies.value = dummyMovies
        Mockito.`when`(filmRepository.getDetailsMovies(moviesId)).thenReturn(movies)
        val movieEntity = viewModel.getDetailMovies().value as FilmEntity
        assertNotNull(movieEntity)
        assertEquals(dummyMovies.filmId, movieEntity.filmId)
        assertEquals(dummyMovies.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovies.link, movieEntity.link)
        assertEquals(dummyMovies.title, movieEntity.title)
        assertEquals(dummyMovies.duration, movieEntity.duration)
        assertEquals(dummyMovies.genre, movieEntity.genre)
        assertEquals(dummyMovies.overview, movieEntity.overview)
        assertEquals(dummyMovies.poster, movieEntity.poster)

        viewModel.getDetailMovies().observeForever(detailObserver)
        verify(detailObserver).onChanged(dummyMovies)
    }
}