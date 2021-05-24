package com.adhitya.katalogfilm.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adhitya.katalogfilm.R
import com.adhitya.katalogfilm.utils.FilmsData
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val moviesData = FilmsData.generateMoviesData()
    private val tvShowsData = FilmsData.generateTVShowsData()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                moviesData.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(moviesData[0].title)))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(moviesData[0].genre)))
    }

    @Test
    fun loadTVShows() {
        onView(withText("Acara TV")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowsData.size
            )
        )
    }

    @Test
    fun loadDetailTVShows() {
        onView(withText("Acara TV")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(tvShowsData[0].title)))
        onView(withId(R.id.text_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.text_genre)).check(matches(withText(tvShowsData[0].genre)))
    }
}