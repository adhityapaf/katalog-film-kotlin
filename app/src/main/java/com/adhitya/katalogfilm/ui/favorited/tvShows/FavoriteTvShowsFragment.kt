package com.adhitya.katalogfilm.ui.favorited.tvShows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhitya.katalogfilm.databinding.FavoriteTvShowsFragmentBinding
import com.adhitya.katalogfilm.ui.favorited.FavoriteMainViewModel
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FavoriteTvShowsFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var fragmentFavoriteTvShowsBinding: FavoriteTvShowsFragmentBinding
    private lateinit var adapter : FavoriteTvShowsAdapter
    private lateinit var viewModel: FavoriteMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentFavoriteTvShowsBinding = FavoriteTvShowsFragmentBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTvShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(this, factory)[FavoriteMainViewModel::class.java]
            adapter = FavoriteTvShowsAdapter()
            fragmentFavoriteTvShowsBinding.favoriteTvShowsProgressbar.visibility = View.VISIBLE
            viewModel.getFavoritedTvShows().observe(viewLifecycleOwner, { tvShows ->
                fragmentFavoriteTvShowsBinding.favoriteTvShowsProgressbar.visibility = View.GONE
                adapter.submitList(tvShows)
            })
            fragmentFavoriteTvShowsBinding.rvFavoriteTvShows.layoutManager = LinearLayoutManager(context)
            fragmentFavoriteTvShowsBinding.rvFavoriteTvShows.setHasFixedSize(true)
            fragmentFavoriteTvShowsBinding.rvFavoriteTvShows.adapter = adapter
        }
    }
}