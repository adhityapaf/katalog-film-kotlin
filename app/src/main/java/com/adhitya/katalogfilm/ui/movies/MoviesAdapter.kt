package com.adhitya.katalogfilm.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adhitya.katalogfilm.data.FilmEntity
import com.adhitya.katalogfilm.databinding.ItemsMoviesBinding
import com.adhitya.katalogfilm.ui.details.DetailFilmActivity
import com.bumptech.glide.Glide

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.FilmViewHolder>() {

    private var listFilms = ArrayList<FilmEntity>()

    fun setFilms(films: List<FilmEntity>?) {
        if (films == null) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmViewHolder {
        val itemsMoviesBinding =
            ItemsMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(itemsMoviesBinding)
    }

    override fun getItemCount(): Int = listFilms.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    class FilmViewHolder(private val binding: ItemsMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmEntity) {
            with(binding) {
                tvItemTitle.text = film.title
                tvItemOverview.text = film.overview
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.FILM_TYPE, "movies")
                    intent.putExtra(DetailFilmActivity.FILM_EXTRA, film.filmId)
                    itemView.context.startActivity(intent)

                }
                Glide.with(itemView.context)
                    .load(film.poster)
                    .into(imagePoster)
            }
        }
    }
}