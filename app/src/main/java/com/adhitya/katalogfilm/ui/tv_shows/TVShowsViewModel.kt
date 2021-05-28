package com.adhitya.katalogfilm.ui.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.utils.FilmsData

class TVShowsViewModel (private val filmRepository: FilmRepository) : ViewModel() {

    private lateinit var filmId: String

    fun getTVShows(): LiveData<List<FilmEntity>> = filmRepository.getTvShows()

    fun setSelectedTVShows(filmId: String) {
        this.filmId = filmId
    }

    fun getDetailTvShows() : LiveData<FilmEntity> = filmRepository.getDetailsTvShows(filmId)
//
//    fun getDetailTVShows(): FilmEntity {
//        lateinit var tv_shows: FilmEntity
//        val TVShowsEntities = FilmsData.generateTVShowsData()
//        for (TVShowsEntity in TVShowsEntities) {
//            if (TVShowsEntity.filmId == filmId) {
//                tv_shows = TVShowsEntity
//            }
//        }
//        return tv_shows
//    }
}