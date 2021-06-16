package com.adhitya.katalogfilm.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhitya.katalogfilm.BuildConfig
import com.adhitya.katalogfilm.data.source.ApiService
import com.adhitya.katalogfilm.data.source.remote.ApiConfig
import com.adhitya.katalogfilm.data.source.remote.ApiResponse
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.data.source.remote.response.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class ApiHelper(private val context: Context) {
    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    private var service: ApiService = ApiConfig.instance

    fun getMoviesList(liveDataMovie: MutableLiveData<MutableList<MoviesResultsItem>>) {
        EspressoIdlingResource.increment()
        val call = service.getListMovies(API_KEY)
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    if (movieResponse != null && movieResponse.results != null) {
                        liveDataMovie.postValue(movieResponse.results)
                    } else {
                        Toast.makeText(context, "Ups, Movie list is empty", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("GetMoviesList", "onResponse: Empty")
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.d("GetMoviesList", "onFailure: Failed to load movie list")
                Toast.makeText(context, "Ups, Movie list failed to load", Toast.LENGTH_SHORT).show()
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getMoviesDetails(movieId: Int, liveDataDetailsMovie: MutableLiveData<MoviesDetailsResponse>) {
        EspressoIdlingResource.increment()
        val call = service.getDetailsMovies(movieId, API_KEY)
        call.enqueue(object : Callback<MoviesDetailsResponse> {
            override fun onResponse(
                call: Call<MoviesDetailsResponse>,
                response: Response<MoviesDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val detailMovieResponse = response.body()
                        liveDataDetailsMovie.postValue(detailMovieResponse)
                    } else {
                        Log.d("getMovieDetails", "onResponse: Detail movie is empty")
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MoviesDetailsResponse>, t: Throwable) {
                Log.d("getMovieDetails", "onFailure: Detail movie is failed to load")
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getTvShowsList(liveDataTvShow: MutableLiveData<MutableList<ResultsItem>>) {
        EspressoIdlingResource.increment()
        val call = service.getListTvShows(API_KEY)
        call.enqueue(object : Callback<TVShowsResponse> {
            override fun onResponse(
                call: Call<TVShowsResponse>,
                response: Response<TVShowsResponse>
            ) {
                if (response.isSuccessful) {
                    val tvShowListResponse = response.body()
                    if (tvShowListResponse != null && tvShowListResponse.results != null) {
                        liveDataTvShow.postValue(tvShowListResponse.results)
                    } else {
                        Log.d("getTvShowsList", "onResponse: TV Shows List is Empty")
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                Log.d("GetTvShowsList", "onFailure: TV Shows list is failed to load")
                EspressoIdlingResource.decrement()
            }
        })
    }

    fun getTvShowsDetails(tv_id: Int, liveDataTvShowDetails: MutableLiveData<TVShowsDetailsResponse>) {
        EspressoIdlingResource.increment()
        val call = service.getDetailsTvShows(tv_id, API_KEY)
        call.enqueue(object : Callback<TVShowsDetailsResponse> {
            override fun onResponse(
                call: Call<TVShowsDetailsResponse>,
                response: Response<TVShowsDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val detailTvShowResponse = response.body()
                        liveDataTvShowDetails.postValue(detailTvShowResponse)
                    } else {
                        Log.d("TvShowsDetail", "onResponse: Detail Tv Shows is empty")
                    }
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TVShowsDetailsResponse>, t: Throwable) {
                Log.d("TvShowsDetails", "onFailure: Detail Tv Shows is faild to load")
                EspressoIdlingResource.decrement()
            }
        })
    }
}