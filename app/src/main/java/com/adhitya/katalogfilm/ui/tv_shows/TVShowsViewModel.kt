package com.adhitya.katalogfilm.ui.tv_shows

import androidx.lifecycle.ViewModel
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.utils.FilmsData

class TVShowsViewModel : ViewModel() {

    private lateinit var filmId: String

    fun getTVShows(): ArrayList<FilmEntity> = FilmsData.generateTVShowsData()

    fun setSelectedTVShows(filmId: String) {
        this.filmId = filmId
    }

    fun getDetailTVShows(): FilmEntity {
        lateinit var tv_shows: FilmEntity
        val TVShowsEntities = FilmsData.generateTVShowsData()
        for (TVShowsEntity in TVShowsEntities) {
            if (TVShowsEntity.filmId == filmId) {
                tv_shows = TVShowsEntity
            }
        }
        return tv_shows
    }
}