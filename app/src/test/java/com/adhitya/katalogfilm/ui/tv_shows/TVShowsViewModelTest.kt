package com.adhitya.katalogfilm.ui.tv_shows

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
import org.mockito.Mock
import org.mockito.Mockito

class TVShowsViewModelTest {
    private lateinit var viewModel: TVShowsViewModel
    private val tvShowsData = FilmsData.generateTVShowsData()[0]
    private val tvShowsId = tvShowsData.filmId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository : FilmRepository

    @Mock
    private lateinit var detailObserver: Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel(filmRepository)
        viewModel.setSelectedTVShows(tvShowsId)
    }

    @Test
    fun getTVShows() {
        val tvShowsListDummy = FilmsData.generateTVShowsData()
        val tvShows = MutableLiveData<List<FilmEntity>>()
        tvShows.value = tvShowsListDummy
        Mockito.`when`(filmRepository.getTvShows()).thenReturn(tvShows)
        val tvShowsEntity = viewModel.getTVShows().value
        verify(filmRepository).getMovies()
        assertNotNull(tvShowsEntity)
        assertEquals(20, tvShowsEntity?.size)
    }

    @Test
    fun getDetailTVShows() {
        val tvShows = MutableLiveData<FilmEntity>()
        tvShows.value = tvShowsData
        Mockito.`when`(filmRepository.getDetailsTvShows(tvShowsId)).thenReturn(tvShows)
        val tvShowsEntity = viewModel.getDetailTvShows().value as FilmEntity
        assertNotNull(tvShowsEntity)
        assertEquals(tvShowsData.filmId, tvShowsEntity.filmId)
        assertEquals(tvShowsData.title, tvShowsEntity.title)
        assertEquals(tvShowsData.releaseDate, tvShowsEntity.releaseDate)
        assertEquals(tvShowsData.genre, tvShowsEntity.genre)
        assertEquals(tvShowsData.duration, tvShowsEntity.duration)
        assertEquals(tvShowsData.overview, tvShowsEntity.overview)
        assertEquals(tvShowsData.poster, tvShowsEntity.poster)

        viewModel.getDetailTvShows().observeForever(detailObserver)
        verify(detailObserver).onChanged(tvShowsData)
    }
}