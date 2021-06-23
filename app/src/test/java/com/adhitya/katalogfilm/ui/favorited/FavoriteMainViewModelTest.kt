package com.adhitya.katalogfilm.ui.favorited

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
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
class FavoriteMainViewModelTest {

    private lateinit var viewModel: FavoriteMainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var favoriteMoviesObserver: Observer<PagedList<FilmEntity>>

    @Mock
    private lateinit var favoriteTvShowsObserver: Observer<PagedList<FilmEntity>>

    @Mock
    private lateinit var favoritedMoviesPagedList : PagedList<FilmEntity>

    @Mock
    private lateinit var favoritedTvShowsPagedList: PagedList<FilmEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteMainViewModel(filmRepository)
    }

    @Test
    fun getFavoritedMovies() {
        val moviesList = favoritedMoviesPagedList
        Mockito.`when`(moviesList.size).thenReturn(4)
        val movies = MutableLiveData<PagedList<FilmEntity>>()
        movies.value = moviesList
        Mockito.`when`(filmRepository.getFavoritedMovies()).thenReturn(movies)
        val favoritedMoviesEntities = viewModel.getFavoritedMovies().value
        Mockito.verify(filmRepository).getFavoritedMovies()
        assertNotNull(favoritedMoviesEntities)
        assertEquals(4, favoritedMoviesEntities?.size)
        viewModel.getFavoritedMovies().observeForever(favoriteMoviesObserver)
        Mockito.verify(favoriteMoviesObserver).onChanged(moviesList)
    }

    @Test
    fun getFavoritedTvShows() {
        val tvShowsList = favoritedTvShowsPagedList
        Mockito.`when`(tvShowsList.size).thenReturn(4)
        val tvShows = MutableLiveData<PagedList<FilmEntity>>()
        tvShows.value = tvShowsList
        Mockito.`when`(filmRepository.getFavoritedTvShows()).thenReturn(tvShows)
        val favoritedTvShowsEntities = viewModel.getFavoritedTvShows().value
        Mockito.verify(filmRepository).getFavoritedTvShows()
        assertNotNull(favoritedTvShowsEntities)
        assertEquals(4, favoritedTvShowsEntities?.size)
        viewModel.getFavoritedTvShows().observeForever(favoriteTvShowsObserver)
        Mockito.verify(favoriteTvShowsObserver).onChanged(tvShowsList)
    }
}