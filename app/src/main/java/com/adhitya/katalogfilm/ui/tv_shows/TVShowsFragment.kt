package com.adhitya.katalogfilm.ui.tv_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhitya.katalogfilm.databinding.FragmentTVShowsBinding

class TVShowsFragment : Fragment() {
    lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(inflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TVShowsViewModel::class.java]
            val tv_shows = viewModel.getTVShows()

            val tvShowsAdapter = TVShowsAdapter()
            tvShowsAdapter.setTvShows(tv_shows)

            with(fragmentTVShowsBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }
}