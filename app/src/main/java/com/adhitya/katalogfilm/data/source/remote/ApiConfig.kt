package com.adhitya.katalogfilm.data.source.remote

import com.adhitya.katalogfilm.data.source.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val client = OkHttpClient.Builder().build()

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(client)
            baseUrl("https://api.themoviedb.org/3/")
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    val instance: ApiService by lazy {
        retrofit.build().create(ApiService::class.java)
    }
}