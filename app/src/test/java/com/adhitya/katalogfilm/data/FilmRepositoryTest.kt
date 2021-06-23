package com.adhitya.katalogfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.adhitya.katalogfilm.data.source.local.LocalDataSource
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.utils.FilmsData
import com.adhitya.katalogfilm.utils.LiveDataTestUtil
import com.adhitya.katalogfilm.utils.PagedListUtil
import com.adhitya.katalogfilm.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote, local)

    private val moviesResponse = FilmsData.generateMoviesData()
    private val moviesDetailResponse = moviesResponse[0]
    private val moviesId = moviesResponse[0].filmId
    private val tvShowsResponse = FilmsData.generateTVShowsData()
    private val tvShowsDetailResponse = tvShowsResponse[0]
    private val tvShowsId = tvShowsResponse[0].filmId

    @Test
    fun getMoviesList(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getMovies()).thenReturn(dataSourceFactory)
        filmRepository.getMovies()
        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(FilmsData.generateMoviesData()))
        Mockito.verify(local).getMovies()
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.data?.size?.toLong())
    }

    @Test
    fun getMoviesDetails() {
        val dummyMovie = MutableLiveData<FilmEntity>()
        dummyMovie.value = moviesDetailResponse
        Mockito.`when`(local.getDetailsMovie(moviesId)).thenReturn(dummyMovie)
        val detailResults = LiveDataTestUtil.getValue(filmRepository.getDetailsMovies(moviesId))
        Mockito.verify(local).getDetailsMovie(moviesId)
        assertNotNull(detailResults)
        assertNotNull(detailResults.title)
        assertEquals(moviesDetailResponse.title, detailResults.title)
    }

    @Test
    fun getTvShowsLists() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getTvShows()
        val tvShowsEntities = Resource.success(PagedListUtil.mockPagedList(FilmsData.generateTVShowsData()))
        Mockito.verify(local).getTvShows()
        assertNotNull(tvShowsEntities.data)
        assertEquals(tvShowsResponse.size.toLong(), tvShowsEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShowsDetails() {
        val dummyTvShows = MutableLiveData<FilmEntity>()
        dummyTvShows.value = tvShowsDetailResponse
        Mockito.`when`(local.getDetailsTvShow(tvShowsId)).thenReturn(dummyTvShows)
        val tvShowsDetailResult = LiveDataTestUtil.getValue(filmRepository.getDetailsTvShows(tvShowsId))
        assertNotNull(tvShowsDetailResult)
        assertNotNull(tvShowsDetailResult.title)
        assertEquals(tvShowsDetailResponse.title, tvShowsDetailResult.title)
    }

    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        filmRepository.getFavoritedMovies()
        val favoritedMoviesEntities = Resource.success(PagedListUtil.mockPagedList(FilmsData.generateMoviesData()))
        Mockito.verify(local).getFavoritedMovies()
        assertNotNull(favoritedMoviesEntities.data)
        assertEquals(moviesResponse.size.toLong(), favoritedMoviesEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoritedTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getFavoritedTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getFavoritedTvShows()
        val favoritedTvShowsEntity = Resource.success(PagedListUtil.mockPagedList(FilmsData.generateTVShowsData()))
        Mockito.verify(local).getFavoritedTvShows()
        assertNotNull(favoritedTvShowsEntity.data)
        assertEquals(tvShowsResponse.size.toLong(), favoritedTvShowsEntity.data?.size?.toLong())
    }
}