package com.adhitya.katalogfilm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.utils.FilmsData
import com.adhitya.katalogfilm.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val filmRepository = FakeFilmRepository(remote)

    private val moviesResponse = FilmsData.generateRemoteMoviesData()
    private val moviesDetailResponse = FilmsData.generateRemoteMoviesDetailsData()
    private val moviesId = moviesResponse[0].id
    private val tvShowsResponse = FilmsData.generateRemoteTvShowsData()
    private val tvShowsDetailResponse = FilmsData.generateRemoteTvShowsDetailsData()
    private val tvShowsId = tvShowsResponse[0].id

    @Test
    fun getMoviesList(){
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesListCallback)
                    .onMoviesListReceived(moviesResponse)
                null
            }.`when`(remote).getMoviesList(any())
        }
        val moviesEntities = LiveDataTestUtil.getValue(filmRepository.getMovies())
        runBlocking {
            Mockito.verify(remote).getMoviesList(any())

        }
        assertNotNull(moviesEntities)
        assertEquals(moviesResponse.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getMoviesDetails() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMoviesDetailsCallback)
                    .onMoviesDetailsReceived(moviesDetailResponse)
                null
            }.`when`(remote).getMoviesDetails(eq(moviesId), any())
        }
        val detailResults = LiveDataTestUtil.getValue(filmRepository.getDetailsMovies(moviesId.toString()))
        runBlocking {
            Mockito.verify(remote).getMoviesDetails(eq(moviesId), any())

        }
        assertNotNull(detailResults)
        assertNotNull(detailResults.title)
        assertEquals(moviesDetailResponse.title, detailResults.title)
    }

    @Test
    fun getTvShowsLists() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowsListCallback)
                    .onTvShowsListReceived(tvShowsResponse)
                null
            }.`when`(remote).getTvShowsList(any())
        }
        val tvShowsEntities = LiveDataTestUtil.getValue(filmRepository.getTvShows())
        runBlocking {
            Mockito.verify(remote).getTvShowsList(any())
        }
        assertNotNull(tvShowsEntities)
        assertEquals(tvShowsResponse.size.toLong(), tvShowsEntities.size.toLong())
    }

    @Test
    fun getTvShowsDetails() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowsDetailsCallback)
                    .onTvShowsDetailsReceived(tvShowsDetailResponse)
                null
            }.`when`(remote).getTvShowsDetails(eq(tvShowsId), any())
        }
        val tvShowsDetailResult = LiveDataTestUtil.getValue(filmRepository.getDetailsTvShows(tvShowsId.toString()))
        runBlocking {
            Mockito.verify(remote).getTvShowsDetails(eq(tvShowsId), any())
        }
        assertNotNull(tvShowsDetailResult)
        assertNotNull(tvShowsDetailResult.title)
        assertEquals(tvShowsDetailResponse.name, tvShowsDetailResult.title)
    }
}