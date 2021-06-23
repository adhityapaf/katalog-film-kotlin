package com.adhitya.katalogfilm.ui.tv_shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhitya.katalogfilm.databinding.FragmentTVShowsBinding
import com.adhitya.katalogfilm.ui.main.MainViewModel
import com.adhitya.katalogfilm.viewmodel.ViewModelFactory
import com.adhitya.katalogfilm.vo.Status
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TVShowsFragment : DaggerFragment() {
    private lateinit var fragmentTVShowsBinding: FragmentTVShowsBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTVShowsBinding = FragmentTVShowsBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MainViewModel::class.java]

            val tvShowsAdapter = TVShowsAdapter()

            viewModel.getTVShows().observe(viewLifecycleOwner, {tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> fragmentTVShowsBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTVShowsBinding.progressBar.visibility = View.GONE
                            tvShowsAdapter.submitList(tvShow.data)
                        }
                        Status.ERROR -> {
                            fragmentTVShowsBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTVShowsBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }
        }
    }
}