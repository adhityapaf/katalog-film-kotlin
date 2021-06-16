package com.adhitya.katalogfilm.di

import android.app.Application
import com.adhitya.katalogfilm.data.source.ApiService
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.data.source.local.LocalDataSource
import com.adhitya.katalogfilm.data.source.local.room.FilmDao
import com.adhitya.katalogfilm.data.source.local.room.FilmDatabase
import com.adhitya.katalogfilm.data.source.remote.RemoteDataSource
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    companion object {

        @Singleton
        @Provides
        fun provideFilmDatabase(application: Application) : FilmDatabase = FilmDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideFilmDao(filmDatabase: FilmDatabase) : FilmDao = filmDatabase.filmDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(filmDao: FilmDao) : LocalDataSource = LocalDataSource(filmDao)

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService) : RemoteDataSource = RemoteDataSource(apiService)

        @Singleton
        @Provides
        fun provideFilmRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ) : FilmRepository = FilmRepository(remoteDataSource, localDataSource)

        @Singleton
        @Provides
        fun provideViewModelFactory(filmRepository: FilmRepository) : ViewModelFactory = ViewModelFactory(filmRepository)
    }
}