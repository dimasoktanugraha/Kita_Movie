package com.dimas.kitamovie.ui.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimas.kitamovie.R
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.utils.Constant
import com.dimas.kitamovie.databinding.ItemMovieBinding
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieResponseViewHolder>(), Filterable {

    private var listData = ArrayList<MovieResponse>()
    private var listDataAll = ArrayList<MovieResponse>()
    var onItemClick: ((MovieResponse) -> Unit)? = null

    fun setData(newListData: List<MovieResponse>?) {
        if (newListData == null) return
        listData.clear()
        listDataAll.clear()
        listData.addAll(newListData)
        listDataAll.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResponseViewHolder  =
        MovieResponseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))


    override fun onBindViewHolder(holder: MovieResponseViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int  = listData.size

    override fun getFilter(): Filter = filter

    inner class MovieResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bind(MovieResponse: MovieResponse) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Constant.IMAGE_URL+MovieResponse.poster_path)
                    .into(itemMovieImage)
                itemMovieTitle.text = MovieResponse.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    private val filter: Filter = object : Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredData = ArrayList<MovieResponse>()
            if (constraint.toString().isEmpty()){
                filteredData.addAll(listDataAll)
            }else{
                for (product in listDataAll){
                    if (product.title.toLowerCase(Locale.ROOT).contains(constraint.toString().toLowerCase(
                            Locale.ROOT))){
                        filteredData.add(product)
                    }
                }
            }
            val filteredResults = FilterResults()
            filteredResults.values = filteredData

            return filteredResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            listData.clear()
            listData.addAll(results!!.values as Collection<MovieResponse>)
            notifyDataSetChanged()
        }
    }
}