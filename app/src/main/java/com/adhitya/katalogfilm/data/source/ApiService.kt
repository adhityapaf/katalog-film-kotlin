package com.adhitya.katalogfilm.data.source

import com.adhitya.katalogfilm.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular")
    fun getListMovies(@Query("api_key") api_key: String) : Call<MoviesResponse>

    @GET("movie/{id}")
    fun getDetailsMovies(
        @Query("api_key") api_key: String,
        @Path("movie_id") movie_id: Int
    ) : Call<MoviesDetailsResponse>

    @GET("tv/popular")
    fun getListTvShows(
        @Query("api_key") api_key: String
    ) : Call<TVShowsResponse>

    @GET("tv/{id}")
    fun getDetailsTvShows(
        @Query("api_key") api_key: String,
        @Path("tv_id") tv_id: Int
    ) : Call<TVShowsDetailsResponse>
}