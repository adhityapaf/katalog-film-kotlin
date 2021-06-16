package com.adhitya.katalogfilm.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhitya.katalogfilm.data.source.FilmRepository
import com.adhitya.katalogfilm.ui.details.DetailFilmViewModel
import com.adhitya.katalogfilm.ui.favorited.FavoriteMainViewModel
import com.adhitya.katalogfilm.ui.main.MainViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val mFilmRepository: FilmRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMainViewModel::class.java) -> {
                return FavoriteMainViewModel(mFilmRepository) as T
            }
            modelClass.isAssignableFrom(DetailFilmViewModel::class.java) -> {
                return DetailFilmViewModel(mFilmRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel Class " + modelClass.name)
        }
    }
}