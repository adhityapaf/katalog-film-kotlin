package com.adhitya.katalogfilm.di

import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): FilmRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return FilmRepository.getInstance(remoteDataSource)
    }
}