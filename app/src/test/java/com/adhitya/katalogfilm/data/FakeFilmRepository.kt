package com.adhitya.katalogfilm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhitya.katalogfilm.data.source.FilmDataSource
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.data.source.remote.response.MoviesDetailsResponse
import com.adhitya.katalogfilm.data.source.remote.response.MoviesResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.ResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.TVShowsDetailsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeFilmRepository (private val remoteDataSource: RemoteDataSource) : FilmDataSource{

    companion object {
        private const val POSTER_PREFIX_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        private const val MOVIE_LINK_PREFIX_URL = "https://www.themoviedb.org/movie/"
        private const val TV_SHOWS_LINK_PREFIX_URL = "https://www.themoviedb.org/tv/"
    }

    override fun getMovies(): LiveData<List<FilmEntity>> {

        val moviesResults = MutableLiveData<List<FilmEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMoviesList(object : RemoteDataSource.LoadMoviesListCallback {
                override fun onMoviesListReceived(moviesResultsItem: List<MoviesResultsItem>) {
                    val moviesList = ArrayList<FilmEntity>()
                    for (movieResult in moviesResultsItem) {
                        val movies = FilmEntity(
                            movieResult.id.toString(),
                            movieResult.title,
                            movieResult.overview,
                            movieResult.releaseDate,
                            getDetailsMovies(movieResult.id.toString()).value?.genre.toString(),
                            POSTER_PREFIX_URL + movieResult.posterPath,
                            "${getDetailsMovies(movieResult.id.toString()).value?.duration.toString()} m",
                            MOVIE_LINK_PREFIX_URL + movieResult.id
                        )
                        moviesList.add(movies)
                    }
                    moviesResults.postValue(moviesList)
                }
            })
        }
        return moviesResults
    }

    override fun getDetailsMovies(filmId: String): LiveData<FilmEntity> {
        val moviesDetailsResult = MutableLiveData<FilmEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMoviesDetails(
                filmId.toInt(),
                object : RemoteDataSource.LoadMoviesDetailsCallback {
                    override fun onMoviesDetailsReceived(moviesDetailsResponse: MoviesDetailsResponse) {
                        val movieDetailsList = FilmEntity(
                            moviesDetailsResponse.id.toString(),
                            moviesDetailsResponse.title,
                            moviesDetailsResponse.overview,
                            moviesDetailsResponse.releaseDate.substring(0, 4),
                            moviesDetailsResponse.genres.joinToString(),
                            POSTER_PREFIX_URL + moviesDetailsResponse.posterPath,
                            "${moviesDetailsResponse.runtime} m",
                            MOVIE_LINK_PREFIX_URL + moviesDetailsResponse.id
                        )
                        moviesDetailsResult.postValue(movieDetailsList)
                    }
                })
        }
        return moviesDetailsResult
    }

    override fun getTvShows(): LiveData<List<FilmEntity>> {
        val tvShowsResult = MutableLiveData<List<FilmEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowsList(object : RemoteDataSource.LoadTvShowsListCallback {
                override fun onTvShowsListReceived(tvShowsResponse: List<ResultsItem>) {
                    val tvShowsList = ArrayList<FilmEntity>()
                    for (tvShowResult in tvShowsResponse) {
                        val tvShows = FilmEntity(
                            tvShowResult.id.toString(),
                            tvShowResult.name,
                            tvShowResult.overview,
                            tvShowResult.firstAirDate,
                            getDetailsTvShows(tvShowResult.id.toString()).value?.genre.toString(),
                            POSTER_PREFIX_URL + tvShowResult.posterPath,
                            getDetailsTvShows(tvShowResult.id.toString()).value?.duration.toString(),
                            TV_SHOWS_LINK_PREFIX_URL + tvShowResult.id
                        )
                        tvShowsList.add(tvShows)
                    }
                    tvShowsResult.postValue(tvShowsList)
                }
            })
        }
        return tvShowsResult
    }

    override fun getDetailsTvShows(filmId: String): LiveData<FilmEntity> {
        val tvShowsDetailsResult = MutableLiveData<FilmEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowsDetails(
                filmId.toInt(),
                object : RemoteDataSource.LoadTvShowsDetailsCallback {
                    override fun onTvShowsDetailsReceived(tvShowsDetailsResponse: TVShowsDetailsResponse) {
                        val tvShowsDetailsList = FilmEntity(
                            tvShowsDetailsResponse.id.toString(),
                            tvShowsDetailsResponse.name,
                            tvShowsDetailsResponse.overview,
                            tvShowsDetailsResponse.firstAirDate.substring(0, 4),
                            tvShowsDetailsResponse.genres.joinToString(),
                            POSTER_PREFIX_URL + tvShowsDetailsResponse.posterPath,
                            tvShowsDetailsResponse.episodeRunTime.joinToString() + "m",
                            TV_SHOWS_LINK_PREFIX_URL + tvShowsDetailsResponse.id
                        )
                        tvShowsDetailsResult.postValue(tvShowsDetailsList)
                    }
                })
        }

        return tvShowsDetailsResult
    }
}