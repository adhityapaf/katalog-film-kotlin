package com.adhitya.katalogfilm.data.source

import com.adhitya.katalogfilm.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular")
    fun getListMovies(@Query("api_key") api_key: String) : Call<MoviesResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovies(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ) : Call<MoviesDetailsResponse>

    @GET("tv/popular")
    fun getListTvShows(
        @Query("api_key") api_key: String
    ) : Call<TVShowsResponse>

    @GET("tv/{tv_id}")
    fun getDetailsTvShows(
        @Path("tv_id") tv_id: Int,
        @Query("api_key") api_key: String
    ) : Call<TVShowsDetailsResponse>
}