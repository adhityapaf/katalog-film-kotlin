package com.adhitya.katalogfilm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TVShowsDetailsResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int>,

)

data class GenresItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

