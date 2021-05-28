package com.adhitya.katalogfilm.utils

import android.content.Context
import android.util.Log
import com.adhitya.katalogfilm.data.source.remote.ApiConfig
import com.adhitya.katalogfilm.data.source.remote.response.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response


class ApiHelper(private val context: Context) {
    companion object {
        const val API_KEY = "95a852f12ee3b7fced1a6343e4f2c221"
        const val TAG = "ApiHelper"
    }

    fun loadMoviesList(): List<MoviesResultsItem> {
        val list = ArrayList<MoviesResultsItem>()
        val client = ApiConfig.getApiService().getListMovies(API_KEY)
        client.enqueue(object : retrofit2.Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.results?.let { list.addAll(it) }
                    Log.d(TAG, "onResponse: $list")
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return list
    }

    fun loadMovieDetails(movie_id: Int) : MoviesDetailsResponse {
        lateinit var movieDetails : MoviesDetailsResponse
        val client = ApiConfig.getApiService().getDetailsMovies(API_KEY, movie_id)
        client.enqueue(object: retrofit2.Callback<MoviesDetailsResponse>{
            override fun onResponse(
                call: Call<MoviesDetailsResponse>,
                response: Response<MoviesDetailsResponse>
            ) {
                if (response.isSuccessful){
                    movieDetails = response.body()!!
                } else {
                    Log.e(TAG, "onResponse : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MoviesDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
        return movieDetails
    }

    fun loadTvShowsList() : List<ResultsItem> {
        val list = ArrayList<ResultsItem>()
        val client = ApiConfig.getApiService().getListTvShows(API_KEY)
        client.enqueue(object: retrofit2.Callback<TVShowsResponse> {
            override fun onResponse(
                call: Call<TVShowsResponse>,
                response: Response<TVShowsResponse>
            ) {
                if (response.isSuccessful) {
                    for (tvShowsItems in response.body()?.results!!) {
                        list.add(tvShowsItems)
                    }
                } else {
                    Log.e(TAG, "onResponse : ${response.message()}")
                }
            }
            override fun onFailure(call: Call<TVShowsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message.toString()}")
            }
        })
        return list
    }

    fun loadTvShowsDetails(tv_id : Int) : TVShowsDetailsResponse{
        lateinit var  tvShowsDetails : TVShowsDetailsResponse
        val client = ApiConfig.getApiService().getDetailsTvShows(API_KEY, tv_id)
        client.enqueue(object: retrofit2.Callback<TVShowsDetailsResponse>{
            override fun onResponse(
                call: Call<TVShowsDetailsResponse>,
                response: Response<TVShowsDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    tvShowsDetails = response.body()!!
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}", )
                }
            }

            override fun onFailure(call: Call<TVShowsDetailsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}", )
            }

        })
        return tvShowsDetails
    }
}