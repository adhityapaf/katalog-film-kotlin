package com.adhitya.katalogfilm.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.vo.Resource
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
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var moviesObserver : Observer<Resource<PagedList<FilmEntity>>>

    @Mock
    private lateinit var tvShowsObserver: Observer<Resource<PagedList<FilmEntity>>>

    @Mock
    private lateinit var moviesPagedList : PagedList<FilmEntity>

    @Mock
    private lateinit var tvShowsPagedList: PagedList<FilmEntity>

    @Before
    fun setUp() {
        viewModel = MainViewModel(filmRepository)
    }

    @Test
    fun getMovies() {
        val moviesList =  Resource.success(moviesPagedList)
        Mockito.`when`(moviesList.data?.size).thenReturn(4)
        val movies = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        movies.value = moviesList
        Mockito.`when`(filmRepository.getMovies()).thenReturn(movies)
        val moviesEntities = viewModel.getMovies().value?.data
        Mockito.verify(filmRepository).getMovies()
        assertNotNull(moviesEntities)
        assertEquals(4, moviesEntities?.size)
        viewModel.getMovies().observeForever(moviesObserver)
        Mockito.verify(moviesObserver).onChanged(moviesList)
    }

    @Test
    fun getTvShows() {
        val tvShowsList = Resource.success(tvShowsPagedList)
        Mockito.`when`(tvShowsList.data?.size).thenReturn(4)
        val tvShows = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        tvShows.value = tvShowsList

        Mockito.`when`(filmRepository.getTvShows()).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getTVShows().value?.data
        Mockito.verify(filmRepository).getTvShows()
        assertNotNull(tvShowsEntities)
        assertEquals(4, tvShowsEntities?.size)
        viewModel.getTVShows().observeForever(tvShowsObserver)
        Mockito.verify(tvShowsObserver).onChanged(tvShowsList)
    }
}