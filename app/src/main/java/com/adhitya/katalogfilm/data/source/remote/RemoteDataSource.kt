package com.adhitya.katalogfilm.data.source.remote

import android.os.Handler
import android.os.Looper
import com.adhitya.katalogfilm.data.source.remote.response.MoviesDetailsResponse
import com.adhitya.katalogfilm.data.source.remote.response.MoviesResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.ResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.TVShowsDetailsResponse
import com.adhitya.katalogfilm.utils.ApiHelper
import com.adhitya.katalogfilm.utils.EspressoIdlingResource


class RemoteDataSource private constructor(private val apiHelper: ApiHelper) {
    private val handler = Handler(Looper.getMainLooper())
    companion object {
        private const val SERVICE_LATENCY_IN_MILLS : Long = 500

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: ApiHelper) : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMoviesList(callback: LoadMoviesListCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({callback.onMoviesListReceived(apiHelper.loadMoviesList())
        EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLS)
    }

    fun getMoviesDetails(movieId: Int, callback: LoadMoviesDetailsCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({callback.onMoviesDetailsReceived(apiHelper.loadMovieDetails(movieId))
        EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLS)
    }

    fun getTvShowsList(callback: LoadTvShowsListCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({callback.onTvShowsListReceived(apiHelper.loadTvShowsList())
        EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLS)
    }

    fun getTvShowsDetails(tv_id: Int, callback: LoadTvShowsDetailsCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({callback.onTvShowsDetailsReceived(apiHelper.loadTvShowsDetails(tv_id))
        EspressoIdlingResource.decrement()}, SERVICE_LATENCY_IN_MILLS)
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