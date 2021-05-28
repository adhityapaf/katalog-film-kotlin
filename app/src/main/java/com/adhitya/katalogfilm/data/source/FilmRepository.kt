package com.adhitya.katalogfilm.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.data.source.remote.ApiConfig
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.data.source.remote.response.*
import retrofit2.Call
import retrofit2.Response

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource) : FilmDataSource {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    companion object {
        private const val API_KEY = "95a852f12ee3b7fced1a6343e4f2c221"
        private const val TAG = "FilmRepository"
        private const val POSTER_PREFIX_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        private const val MOVIE_LINK_PREFIX_URL = "https://www.themoviedb.org/movie/"
        private const val TV_SHOWS_LINK_PREFIX_URL = "https://www.themoviedb.org/tv/"
        @Volatile
        private var instance : FilmRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource) : FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getMovies(): LiveData<List<FilmEntity>> {
        val moviesResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getMoviesList(object: RemoteDataSource.LoadMoviesListCallback {
            override fun onMoviesListReceived(moviesResultsItem: List<MoviesResultsItem>) {
                val moviesList = ArrayList<FilmEntity>()
                for (movieResult in moviesResultsItem){
                    val movies = FilmEntity(movieResult.id.toString(), movieResult.title, movieResult.overview, movieResult.releaseDate, getDetailsMovies(movieResult.id.toString()).value?.genre.toString(), POSTER_PREFIX_URL+movieResult.posterPath,
                        getDetailsMovies(movieResult.id.toString()).value?.duration.toString(),
                        MOVIE_LINK_PREFIX_URL+movieResult.id)
                    moviesList.add(movies)
                }
                Log.d(TAG, "onMoviesListReceived: $moviesList")
                moviesResults.postValue(moviesList)
            }
        })
        return moviesResults
    }

    override fun getDetailsMovies(filmId: String): LiveData<FilmEntity> {
        val moviesDetailsResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getMoviesDetails(filmId.toInt(), object: RemoteDataSource.LoadMoviesDetailsCallback {
            override fun onMoviesDetailsReceived(moviesDetailsResponse: MoviesDetailsResponse) {
                val movieDetailsList = FilmEntity(moviesDetailsResponse.id.toString(), moviesDetailsResponse.title, moviesDetailsResponse.overview, moviesDetailsResponse.releaseDate, moviesDetailsResponse.genres.joinToString(), POSTER_PREFIX_URL+moviesDetailsResponse.posterPath, "${moviesDetailsResponse.runtime} m", MOVIE_LINK_PREFIX_URL+moviesDetailsResponse.id )
                moviesDetailsResult.postValue(movieDetailsList)
            }
        })
        return moviesDetailsResult
    }

    override fun getTvShows(): LiveData<List<FilmEntity>> {
        val tvShowsResult = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getTvShowsList(object: RemoteDataSource.LoadTvShowsListCallback {
            override fun onTvShowsListReceived(tvShowsResponse: List<ResultsItem>) {
                val tvShowsList = ArrayList<FilmEntity>()
                for (tvShowResult in tvShowsResponse) {
                    val tvShows = FilmEntity(tvShowResult.id.toString(), tvShowResult.name, tvShowResult.overview, tvShowResult.firstAirDate, getDetailsTvShows(tvShowResult.id.toString()).value?.genre.toString(), POSTER_PREFIX_URL+tvShowResult.posterPath, getDetailsTvShows(tvShowResult.id.toString()).value?.duration.toString(), TV_SHOWS_LINK_PREFIX_URL+tvShowResult.id)
                    tvShowsList.add(tvShows)
                }
                tvShowsResult.postValue(tvShowsList)
            }
        })
        return tvShowsResult
    }

    override fun getDetailsTvShows(filmId: String): LiveData<FilmEntity> {
        val tvShowsDetailsResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getTvShowsDetails(filmId.toInt(), object: RemoteDataSource.LoadTvShowsDetailsCallback {
            override fun onTvShowsDetailsReceived(tvShowsDetailsResponse: TVShowsDetailsResponse) {
                val tvShowsDetailsList = FilmEntity(tvShowsDetailsResponse.id.toString(), tvShowsDetailsResponse.name, tvShowsDetailsResponse.overview, tvShowsDetailsResponse.firstAirDate, tvShowsDetailsResponse.genres.joinToString(), POSTER_PREFIX_URL+tvShowsDetailsResponse.posterPath, tvShowsDetailsResponse.episodeRunTime[0].toString(), TV_SHOWS_LINK_PREFIX_URL+tvShowsDetailsResponse.id)
                tvShowsDetailsResult.postValue(tvShowsDetailsList)
            }
        })
        return tvShowsDetailsResult
    }

}