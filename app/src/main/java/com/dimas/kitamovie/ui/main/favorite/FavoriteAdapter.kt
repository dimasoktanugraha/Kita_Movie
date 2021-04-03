package com.dimas.kitamovie.ui.main.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimas.kitamovie.R
import com.dimas.kitamovie.core.data.source.local.entity.FavoriteEntity
import com.dimas.kitamovie.core.utils.Constant
import com.dimas.kitamovie.databinding.ItemFavoriteBinding
import com.dimas.kitamovie.databinding.ItemMovieBinding
import java.util.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteEntityViewHolder>() {

    private var listData = ArrayList<FavoriteEntity>()
    var onItemClick: ((FavoriteEntity) -> Unit)? = null

    fun setData(newListData: List<FavoriteEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteEntityViewHolder  =
        FavoriteEntityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))


    override fun onBindViewHolder(holder: FavoriteEntityViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int  = listData.size

    inner class FavoriteEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoriteBinding.bind(itemView)
        fun bind(data: FavoriteEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.IMAGE_URL+data.poster_path)
                    .into(itemMovieImage)
                itemMovieTitle.text = data.title
                itemMovieRate.text = data.vote_average.toString()
                itemMovieDate.text = data.release_date
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}