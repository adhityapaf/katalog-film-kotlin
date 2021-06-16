package com.adhitya.katalogfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "movieTitle")
    var movieTitle: String,

    @ColumnInfo(name = "movieOverview")
    var movieOverview: String,

    @ColumnInfo(name = "movieReleaseDate")
    var movieReleaseDate: String,

    @ColumnInfo(name = "movieGenre")
    var movieGenre: String,

    @ColumnInfo(name = "moviePoster")
    var moviePoster: String,

    @ColumnInfo(name = "movieDuration")
    var movieDuration: String,

    @ColumnInfo(name = "movieLink")
    var movieLink: String,

    @ColumnInfo(name = "movieFavorited")
    var movieFavorited: Boolean = false
)
