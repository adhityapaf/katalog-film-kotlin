package com.adhitya.katalogfilm.data

data class FilmEntity(
    var filmId: String,
    var title: String,
    var overview: String,
    var release_date: String,
    var genre: String,
    var poster: Int,
    var duration: String,
    var link: String
)