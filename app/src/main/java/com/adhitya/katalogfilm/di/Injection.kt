package com.adhitya.katalogfilm.di

import android.content.Context
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.utils.ApiHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiHelper(context))
        return FilmRepository.getInstance(remoteDataSource)
    }
}