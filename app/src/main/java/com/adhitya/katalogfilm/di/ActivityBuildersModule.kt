package com.adhitya.katalogfilm.di

import com.adhitya.katalogfilm.di.main.MainFragmentBuildersModule
import com.adhitya.katalogfilm.di.main.favorited.FavoritedFragmentBuildersModule
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.adhitya.katalogfilm.ui.favorited.FavoriteMainActivity
import com.adhitya.katalogfilm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [FavoritedFragmentBuildersModule::class])
    abstract fun contributeFavoriteMainActivity() : FavoriteMainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailFilmActivity() : DetailFilmActivity
}