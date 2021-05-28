package com.adhitya.katalogfilm.data

data class FilmEntity(
    var filmId: String,
    var title: String,
    var overview: String,
    var releaseDate: String,
    var genre: String,
    var poster: String,
    var duration: String,
    var link: String
)