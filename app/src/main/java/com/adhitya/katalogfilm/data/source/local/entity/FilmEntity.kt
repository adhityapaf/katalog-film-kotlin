package com.adhitya.katalogfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmentities")
data class FilmEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    var filmId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "link")
    var link: String,

    @ColumnInfo(name = "filmType")
    var filmType: String,

    @ColumnInfo(name = "favorited")
    var favorited : Boolean = false
)