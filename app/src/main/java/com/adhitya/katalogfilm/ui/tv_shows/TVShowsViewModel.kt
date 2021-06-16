package com.adhitya.katalogfilm.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.entity.TvShowEntity
import com.adhitya.katalogfilm.vo.Resource

class TVShowsViewModel (private val filmRepository: FilmRepository) : ViewModel() {

    private lateinit var tvShowId: String

    fun getTVShows(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getTvShows()

    fun setSelectedTVShows(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getDetailTvShows() : LiveData<FilmEntity> = filmRepository.getDetailsTvShows(tvShowId)
}