package com.adhitya.katalogfilm.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adhitya.katalogfilm.BuildConfig
import com.adhitya.katalogfilm.data.source.ApiService
import com.adhitya.katalogfilm.data.source.remote.response.MoviesDetailsResponse
import com.adhitya.katalogfilm.data.source.remote.response.MoviesResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.ResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.TVShowsDetailsResponse
import com.adhitya.katalogfilm.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val filmApiService: ApiService) {

    companion object {
        const val API_KEY = BuildConfig.API_KEY
    }

    fun getMoviesList(): LiveData<ApiResponse<List<MoviesResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = filmApiService.getListMovies(API_KEY).await()
                resultMovies.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovies.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovies
    }

    fun getMoviesDetails(movieId: Int): LiveData<ApiResponse<MoviesDetailsResponse>> {
        EspressoIdlingResource.increment()
        val resultDetailsMovie = MutableLiveData<ApiResponse<MoviesDetailsResponse>>()
        CoroutineScope(IO).launch {
            try {
                filmApiService.getDetailsMovies(movieId, API_KEY).await().let { movieDetails ->
                    resultDetailsMovie.postValue(ApiResponse.success(movieDetails))
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultDetailsMovie
    }

    fun getTvShowsList(): LiveData<ApiResponse<List<ResultsItem>>> {
        EspressoIdlingResource.increment()
        val resultTvShowsList = MutableLiveData<ApiResponse<List<ResultsItem>>>()
        CoroutineScope(IO).launch {
            try {
                val response = filmApiService.getListTvShows(API_KEY).await()
                resultTvShowsList.postValue(ApiResponse.success(response.results))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTvShowsList.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowsList
    }

    fun getTvShowsDetails(tv_id: Int): LiveData<ApiResponse<TVShowsDetailsResponse>> {
        EspressoIdlingResource.increment()
        val resultTvShowsDetails = MutableLiveData<ApiResponse<TVShowsDetailsResponse>>()
        CoroutineScope(IO).launch {
            try {
                val response = filmApiService.getDetailsTvShows(tv_id, API_KEY).await()
                resultTvShowsDetails.postValue(ApiResponse.success(response))
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        EspressoIdlingResource.decrement()
        return resultTvShowsDetails
    }
}