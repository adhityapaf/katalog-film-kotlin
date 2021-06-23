package com.adhitya.katalogfilm.ui.tv_shows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.databinding.ItemsTvShowsBinding
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.bumptech.glide.Glide

class TVShowsAdapter : PagedListAdapter<FilmEntity, TVShowsAdapter.TVShowsViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<FilmEntity>() {
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
    ): TVShowsViewHolder {
        val itemsTVShowsBinding =
            ItemsTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemsTVShowsBinding)
    }

    override fun onBindViewHolder(holder: TVShowsViewHolder, position: Int) {
        val tv_shows = getItem(position)
        if (tv_shows != null) {
            holder.bind(tv_shows)
        }
    }

    class TVShowsViewHolder(private val binding: ItemsTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv_shows: FilmEntity) {
            with(binding) {
                tvItemTitle.text = tv_shows.title
                tvItemOverview.text = tv_shows.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.FILM_TYPE, "tv_shows")
                    intent.putExtra(DetailFilmActivity.FILM_EXTRA, tv_shows.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(tv_shows.poster)
                    .into(imagePoster)
            }
        }
    }
}