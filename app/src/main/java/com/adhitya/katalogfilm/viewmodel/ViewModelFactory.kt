package com.adhitya.katalogfilm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.di.Injection
import com.adhitya.katalogfilm.ui.movies.MoviesViewModel
import com.adhitya.katalogfilm.ui.tv_shows.TVShowsViewModel

class ViewModelFactory private constructor(private val mFilmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(TVShowsViewModel::class.java) -> {
                return TVShowsViewModel(mFilmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class " + modelClass.name)
        }
    }
}