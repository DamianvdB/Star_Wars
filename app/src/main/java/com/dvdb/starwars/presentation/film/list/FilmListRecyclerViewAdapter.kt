package com.dvdb.starwars.presentation.film.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dvdb.starwars.R
import com.dvdb.starwars.common.model.FilmListItem
import kotlinx.android.synthetic.main.item_film.view.*

class FilmListRecyclerViewAdapter(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private var filmItems: List<FilmListItem> = emptyList()
) : RecyclerView.Adapter<FilmListRecyclerViewAdapter.FilmViewHolder>() {
    private val glideRequestOptions = RequestOptions().placeholder(R.drawable.ic_darth_vader)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val item = filmItems[position]
        setImageFromItem(holder, item)
        holder.textTitle.text = item.title
        holder.textReleaseDate.text = item.releaseDate
        holder.textDirector.text = item.director
        holder.textProducer.text = item.producer
    }

    private fun setImageFromItem(holder: FilmViewHolder, item: FilmListItem) {
        Glide.with(context)
            .load(item.filmUrl)
            .apply(glideRequestOptions)
            .into(holder.image)
    }

    override fun getItemCount(): Int = filmItems.count()

    fun updateItems(items: List<FilmListItem>) {
        filmItems = items
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.image_film!!
        val textTitle = itemView.text_film_title!!
        val textReleaseDate = itemView.text_release_date!!
        val textDirector = itemView.text_director!!
        val textProducer = itemView.text_producer!!
    }
}