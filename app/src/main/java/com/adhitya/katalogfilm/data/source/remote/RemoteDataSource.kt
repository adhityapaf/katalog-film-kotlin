package com.adhitya.katalogfilm.data.source.remote

import com.adhitya.katalogfilm.data.source.remote.response.MoviesDetailsResponse
import com.adhitya.katalogfilm.data.source.remote.response.MoviesResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.ResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.TVShowsDetailsResponse
import com.adhitya.katalogfilm.utils.EspressoIdlingResource
import retrofit2.await


class RemoteDataSource {
    companion object {

        const val API_KEY = "95a852f12ee3b7fced1a6343e4f2c221"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance() : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    suspend fun getMoviesList(callback: LoadMoviesListCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getListMovies(API_KEY).await().results.let { movieList ->
            callback.onMoviesListReceived(movieList)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getMoviesDetails(movieId: Int, callback: LoadMoviesDetailsCallback){
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailsMovies(movieId, API_KEY).await().let { movieDetails ->
            callback.onMoviesDetailsReceived(movieDetails)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowsList(callback: LoadTvShowsListCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getListTvShows(API_KEY).await().results.let { tvShowsList ->
            callback.onTvShowsListReceived(tvShowsList)
            EspressoIdlingResource.decrement()
        }
    }

    suspend fun getTvShowsDetails(tv_id: Int, callback: LoadTvShowsDetailsCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getDetailsTvShows(tv_id, API_KEY).await().let { tvShowsDetails ->
            callback.onTvShowsDetailsReceived(tvShowsDetails)
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesListCallback {
        fun onMoviesListReceived(moviesResultsItem: List<MoviesResultsItem>)
    }

    interface LoadMoviesDetailsCallback {
        fun onMoviesDetailsReceived(moviesDetailsResponse: MoviesDetailsResponse)
    }

    interface LoadTvShowsListCallback {
        fun onTvShowsListReceived(tvShowsResponse: List<ResultsItem>)
    }

    interface LoadTvShowsDetailsCallback {
        fun onTvShowsDetailsReceived(tvShowsDetailsResponse: TVShowsDetailsResponse)
    }
}