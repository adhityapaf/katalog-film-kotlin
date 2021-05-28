package com.adhitya.katalogfilm.ui.tv_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhitya.katalogfilm.databinding.FragmentTVShowsBinding
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory

class TVShowsFragment : Fragment() {
    lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[TVShowsViewModel::class.java]
            val tvShows = viewModel.getTVShows()

            val tvShowsAdapter = TVShowsAdapter()
            fragmentTVShowsBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTVShows().observe(viewLifecycleOwner, {tvShow ->
                fragmentTVShowsBinding.progressBar.visibility = View.GONE
                tvShowsAdapter.setTvShows(tvShow)
            })

            with(fragmentTVShowsBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }
}