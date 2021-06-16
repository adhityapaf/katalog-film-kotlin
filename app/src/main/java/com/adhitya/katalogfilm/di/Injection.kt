package com.adhitya.katalogfilm.di

import android.content.Context
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.LocalDataSource
import com.adhitya.katalogfilm.data.source.local.room.FilmDatabase
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.utils.AppExecutors

//object Injection {
//    fun provideRepository(context: Context): FilmRepository {
//
//        val database = FilmDatabase.getInstance(context)
//        val remoteDataSource = RemoteDataSource.getInstance()
//        val localDataSource = LocalDataSource.getInstance(database.filmDao())
//        val appExecutors = AppExecutors()
//        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
//    }
//}