package com.dvdb.starwars.presentation.film.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dvdb.starwars.R
import com.dvdb.starwars.common.model.FilmListItem
import com.dvdb.starwars.presentation.util.ListItemClickListener
import kotlinx.android.synthetic.main.item_film.view.*

internal class FilmListRecyclerViewAdapter(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private var filmItems: List<FilmListItem> = emptyList(),
    private val onClickListener: ListItemClickListener<FilmListItem>
) : RecyclerView.Adapter<FilmListRecyclerViewAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            layoutInflater.inflate(R.layout.item_film, parent, false),
            this
        )
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
            .into(holder.image)
    }

    override fun getItemCount(): Int = filmItems.count()

    fun updateItems(items: List<FilmListItem>) {
        filmItems = items
        notifyDataSetChanged()
    }

    class FilmViewHolder(
        itemView: View,
        private val adapter: FilmListRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val image = itemView.image_film!!
        val textTitle = itemView.text_detail_title!!
        val textReleaseDate = itemView.text_detail_release_date!!
        val textDirector = itemView.text_director!!
        val textProducer = itemView.text_producer!!

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            adapter.onClickListener.onItemClicked(v!!, adapter.filmItems[adapterPosition])
        }
    }
}