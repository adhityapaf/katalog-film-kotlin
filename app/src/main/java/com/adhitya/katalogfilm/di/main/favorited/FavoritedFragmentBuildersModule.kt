package com.adhitya.katalogfilm.di.main.favorited

import com.adhitya.katalogfilm.ui.favorited.movies.FavoriteMoviesFragment
import com.adhitya.katalogfilm.ui.favorited.tvShows.FavoriteTvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoritedFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFavoriteMoviesFragment() : FavoriteMoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteTvShowsFragment() : FavoriteTvShowsFragment
}