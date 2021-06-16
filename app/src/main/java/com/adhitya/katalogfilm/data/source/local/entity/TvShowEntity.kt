package com.adhitya.katalogfilm.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: String,

    @ColumnInfo(name = "tvShowTitle")
    var tvShowTitle: String,

    @ColumnInfo(name = "tvShowOverview")
    var tvShowOverview: String,

    @ColumnInfo(name = "tvShowReleaseDate")
    var tvShowReleaseDate: String,

    @ColumnInfo(name = "tvShowGenre")
    var tvShowGenre: String,

    @ColumnInfo(name = "tvShowPoster")
    var tvShowPoster: String,

    @ColumnInfo(name = "tvShowDuration")
    var tvShowDuration: String,

    @ColumnInfo(name = "tvShowLink")
    var tvShowLink: String,

    @ColumnInfo(name = "tvShowFavorited")
    var tvShowFavorited: Boolean = false
) 