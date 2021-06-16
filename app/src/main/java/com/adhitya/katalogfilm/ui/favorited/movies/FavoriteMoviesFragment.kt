package com.adhitya.katalogfilm.ui.favorited.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhitya.katalogfilm.databinding.FavoriteMoviesFragmentBinding
import com.adhitya.katalogfilm.ui.favorited.FavoriteMainViewModel
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteMoviesFragment : DaggerFragment() {

    private lateinit var viewModel: FavoriteMainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var fragmentFavoriteMoviesBinding: FavoriteMoviesFragmentBinding
    private lateinit var adapter: FavoriteMoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteMoviesBinding = FavoriteMoviesFragmentBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMoviesBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this,
            factory)[FavoriteMainViewModel::class.java]

            adapter = FavoriteMoviesAdapter()
            fragmentFavoriteMoviesBinding.favoriteMovieProgressbar.visibility = View.VISIBLE
            viewModel.getFavoritedMovies().observe(viewLifecycleOwner, { movies ->
                fragmentFavoriteMoviesBinding.favoriteMovieProgressbar.visibility = View.GONE
                adapter.submitList(movies)
            })
            fragmentFavoriteMoviesBinding.rvFavoriteMovies.layoutManager = LinearLayoutManager(context)
            fragmentFavoriteMoviesBinding.rvFavoriteMovies.setHasFixedSize(true)
            fragmentFavoriteMoviesBinding.rvFavoriteMovies.adapter = adapter
        }
    }
}