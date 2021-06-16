package com.adhitya.katalogfilm.ui.favorited.tvShows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.databinding.ItemsFavoriteTvShowsBinding
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.bumptech.glide.Glide

class FavoriteTvShowsAdapter : PagedListAdapter<FilmEntity, FavoriteTvShowsAdapter.FavoriteTvShowsViewHolder>(DIFF_CALLBACK){
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FilmEntity>() {
            override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem.filmId == newItem.filmId
            }

            override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTvShowsAdapter.FavoriteTvShowsViewHolder {
        val itemFavoriteTvShowsBinding = ItemsFavoriteTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowsViewHolder(itemFavoriteTvShowsBinding)
    }

    override fun onBindViewHolder(
        holder: FavoriteTvShowsViewHolder,
        position: Int
    ) {
        val tvShows = getItem(position)
        if (tvShows != null) {
            holder.bind(tvShows)
        }
    }

    inner class FavoriteTvShowsViewHolder(private val binding : ItemsFavoriteTvShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: FilmEntity) {
            with(binding) {
                tvItemTitle.text = tvShows.title
                tvItemOverview.text = tvShows.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.FILM_TYPE, "tv_shows")
                    intent.putExtra(DetailFilmActivity.FILM_EXTRA, tvShows.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tvShows.poster)
                    .into(imagePoster)
            }
        }
    }
}