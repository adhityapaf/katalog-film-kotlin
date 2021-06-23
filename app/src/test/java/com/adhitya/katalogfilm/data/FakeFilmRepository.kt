package com.adhitya.katalogfilm.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.FilmDataSource
import com.adhitya.katalogfilm.data.source.local.LocalDataSource
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.remote.ApiResponse
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.data.source.remote.response.MoviesResultsItem
import com.adhitya.katalogfilm.data.source.remote.response.ResultsItem
import com.adhitya.katalogfilm.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class FakeFilmRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    FilmDataSource {

    companion object {
        private const val POSTER_PREFIX_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
        private const val MOVIE_LINK_PREFIX_URL = "https://www.themoviedb.org/movie/"
        private const val TV_SHOWS_LINK_PREFIX_URL = "https://www.themoviedb.org/tv/"

    }

    override fun getMovies(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FilmEntity>, List<MoviesResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResultsItem>>> =
                remoteDataSource.getMoviesList()

            override fun saveCallResult(data: List<MoviesResultsItem>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data) {
                    val movie = FilmEntity(
                        response.id.toString(),
                        response.title,
                        response.overview,
                        response.releaseDate,
                        getDetailsMovies(response.id.toString()).value?.genre.toString(),
                        POSTER_PREFIX_URL + response.posterPath,
                        "${getDetailsMovies(response.id.toString()).value?.duration.toString()}m",
                        MOVIE_LINK_PREFIX_URL + response.id,
                        "movies",
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun getDetailsMovies(movieId: String): LiveData<FilmEntity> =
        localDataSource.getDetailsMovie(movieId)

    override fun setFavoriteMovie(movie: FilmEntity, state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setMovieFavorite(movie, state)
        }
    }

    override fun getTvShows(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object :
            NetworkBoundResource<PagedList<FilmEntity>, List<ResultsItem>>() {
            override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getTvShowsList()

            override fun saveCallResult(data: List<ResultsItem>) {
                val tvShowsList = ArrayList<FilmEntity>()
                for (tvShowResult in data) {
                    val tvShows = FilmEntity(
                        tvShowResult.id.toString(),
                        tvShowResult.name,
                        tvShowResult.overview,
                        tvShowResult.firstAirDate,
                        getDetailsTvShows(tvShowResult.id.toString()).value?.genre.toString(),
                        POSTER_PREFIX_URL + tvShowResult.posterPath,
                        getDetailsTvShows(tvShowResult.id.toString()).value?.duration.toString(),
                        TV_SHOWS_LINK_PREFIX_URL + tvShowResult.id,
                        "tv_shows",
                        false
                    )
                    tvShowsList.add(tvShows)
                }
                localDataSource.insertTvShows(tvShowsList)
            }
        }.asLiveData()
    }

    override fun getFavoritedTvShows(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShows(), config).build()
    }

    override fun getDetailsTvShows(tvShowId: String): LiveData<FilmEntity> =
        localDataSource.getDetailsTvShow(tvShowId)

    override fun setFavoriteTvShow(tvShow: FilmEntity, state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setTvShowFavorite(tvShow, state)
        }
    }
}