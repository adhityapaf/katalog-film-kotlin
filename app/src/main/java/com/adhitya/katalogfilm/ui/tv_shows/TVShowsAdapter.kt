package com.adhitya.katalogfilm.ui.tv_shows

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.databinding.ItemsTvShowsBinding
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.bumptech.glide.Glide

class TVShowsAdapter : RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder>() {

    private var listTVShows = ArrayList<FilmEntity>()

    fun setTvShows(tv_shows: ArrayList<FilmEntity>?) {
        if (tv_shows == null) return
        this.listTVShows.clear()
        this.listTVShows.addAll(tv_shows)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TVShowsAdapter.TVShowsViewHolder {
        val itemsTVShowsBinding =
            ItemsTvShowsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowsViewHolder(itemsTVShowsBinding)
    }

    override fun getItemCount(): Int = listTVShows.size

    override fun onBindViewHolder(holder: TVShowsAdapter.TVShowsViewHolder, position: Int) {
        val tv_shows = listTVShows[position]
        holder.bind(tv_shows)
    }

    class TVShowsViewHolder(private val binding: ItemsTvShowsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv_shows: FilmEntity) {
            with(binding) {
                tvItemTitle.text = tv_shows.title
                tvItemGenre.text = tv_shows.genre
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