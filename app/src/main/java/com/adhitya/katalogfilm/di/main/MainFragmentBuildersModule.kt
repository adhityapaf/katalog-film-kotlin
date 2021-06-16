package com.adhitya.katalogfilm.di.main

import com.adhitya.katalogfilm.ui.movies.MoviesFragment
import com.adhitya.katalogfilm.ui.tv_shows.TVShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMoviesFragment() : MoviesFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TVShowsFragment
}