package com.adhitya.katalogfilm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesDetailsResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("genres")
	val genres: List<MoviesGenresItem>,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("release_date")
	val releaseDate: String,
)


data class MoviesGenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

