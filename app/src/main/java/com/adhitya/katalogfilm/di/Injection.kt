package com.adhitya.katalogfilm.di

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