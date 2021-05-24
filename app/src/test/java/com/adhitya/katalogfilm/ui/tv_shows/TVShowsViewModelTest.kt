package com.adhitya.katalogfilm.ui.tv_shows

import com.adhitya.katalogfilm.ui.movies.MoviesViewModel
import com.adhitya.katalogfilm.utils.FilmsData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TVShowsViewModelTest {
    private lateinit var viewModel: TVShowsViewModel
    private val tvShowsData = FilmsData.generateTVShowsData()[0]
    private val tvShowsId = tvShowsData.filmId

    @Before
    fun setUp() {
        viewModel = TVShowsViewModel()
        viewModel.setSelectedTVShows(tvShowsId)
    }

    @Test
    fun getTVShows() {
        val tvShowsEntity = viewModel.getTVShows()
        assertNotNull(tvShowsEntity)
        assertEquals(20, tvShowsEntity.size)
    }

    @Test
    fun getDetailTVShows() {
        val tvShowsEntity = viewModel.getDetailTVShows()
        assertNotNull(tvShowsEntity)
        assertEquals(tvShowsData.filmId, tvShowsEntity.filmId)
        assertEquals(tvShowsData.title, tvShowsEntity.title)
        assertEquals(tvShowsData.release_date, tvShowsEntity.release_date)
        assertEquals(tvShowsData.genre, tvShowsEntity.genre)
        assertEquals(tvShowsData.duration, tvShowsEntity.duration)
        assertEquals(tvShowsData.overview, tvShowsEntity.overview)
        assertEquals(tvShowsData.poster, tvShowsEntity.poster)
    }
}