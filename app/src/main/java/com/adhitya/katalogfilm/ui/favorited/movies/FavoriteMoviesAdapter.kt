package com.adhitya.katalogfilm.ui.favorited.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adhitya.katalogfilm.data.source.local.entity.FilmEntity
import com.adhitya.katalogfilm.databinding.ItemsFavoriteMoviesBinding
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.bumptech.glide.Glide

class FavoriteMoviesAdapter : PagedListAdapter<FilmEntity, FavoriteMoviesAdapter.FavoriteMoviesViewHolder>(DIFF_CALLBACK){

    companion object{
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
    ): FavoriteMoviesAdapter.FavoriteMoviesViewHolder {
        val itemFavoriteMoviesBinding = ItemsFavoriteMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMoviesViewHolder(itemFavoriteMoviesBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies)
        }
    }

    inner class FavoriteMoviesViewHolder(private val binding: ItemsFavoriteMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: FilmEntity) {
            with(binding) {
                tvItemTitle.text = movies.title
                tvItemOverview.text = movies.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.FILM_TYPE, "movies")
                    intent.putExtra(DetailFilmActivity.FILM_EXTRA, movies.filmId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(movies.poster)
                    .into(imagePoster)
            }
        }
    }
}