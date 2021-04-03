package com.dimas.kitamovie.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dimas.kitamovie.R
import com.dimas.kitamovie.core.data.source.remote.response.MovieResponse
import com.dimas.kitamovie.core.data.source.remote.response.ReviewResponse
import com.dimas.kitamovie.core.utils.Constant
import com.dimas.kitamovie.databinding.ItemMovieBinding
import com.dimas.kitamovie.databinding.ItemReviewBinding
import java.util.*

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var listData = ArrayList<ReviewResponse>()

    fun setData(newListData: List<ReviewResponse>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder  =
        ReviewViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false))


    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int  = listData.size

    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReviewBinding.bind(itemView)
        fun bind(review: ReviewResponse) {
            with(binding) {
                itemReviewName.text = review.author
                itemReviewContent.text = review.content
            }
        }
    }

}